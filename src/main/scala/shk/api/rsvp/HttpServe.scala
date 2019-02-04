package shk.api.rsvp

import scala.io.StdIn

import scala.concurrent.duration._

import akka.actor._
import akka.pattern.ask
import akka.util.Timeout

import akka.routing.RoundRobinPool

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import akka.stream.ActorMaterializer

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json._

import ch.megard.akka.http.cors.scaladsl.CorsDirectives._

import shk.data.rsvp.business.{ Guest }
import shk.api.rsvp.business._
import shk.api.rsvp.actors._


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val responseFailureFormat = jsonFormat1(ResponseFailure)
  implicit val responseSuccessFormat = jsonFormat1(ResponseSuccess)

  implicit val guestFormat = jsonFormat7(Guest)
  implicit val guestResponseSuccessFormat = jsonFormat1(GuestResponseSuccess)

}

object HttpServe extends Directives with JsonSupport {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val guestHandler: ActorRef = system.actorOf(RoundRobinPool(Config.actorPool).props(GuestHandler.props))
  val sendCampaign: ActorRef = system.actorOf(RoundRobinPool(Config.actorPool).props(SendCampaign.props))

  // this is really disgusting
  // this must be serialized when I have some time...
  def toCampaignVars(gust: Guest): String = (
    s"name:${gust.name},lastname:${gust.lastname},country:${gust.country},events:${gust.eventPlace.get}"
  )

  def run = {

    //Define the route
    val route : Route = cors() {

      implicit val timeout = Timeout(Config.actorTimeout seconds)

      path(Constants.GUESTS) {
        options {
          complete("Supported methods : POST")
        } ~
        post {
          entity(as[Guest]) { gust =>
            onSuccess(guestHandler ? GuestPostRequest(gust)) {
              case response: HttpResponse =>
                response match {
                  case GuestResponseSuccess(resp) ⇒
                    sendCampaign ! CampaignPostRequest(Constants.NEW_JOIN, toCampaignVars(gust))
                    complete(StatusCodes.OK, resp)
                  case ResponseFailure(msg) ⇒ complete(StatusCodes.NotFound, ResponseFailure(msg))
                }
              case _ =>
                complete(StatusCodes.InternalServerError)
            }
          }
        }
      }

    }

    val bindingFuture = Http().bindAndHandle(route, Config.httpHost, Config.httpPort)
    // StdIn.readLine()
    // bindingFuture
    //   .flatMap(_.unbind())
    //  .onComplete(_ => system.terminate())
  }
}
