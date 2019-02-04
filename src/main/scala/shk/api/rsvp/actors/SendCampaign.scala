package shk.api.rsvp.actors

import scala.util.{ Failure, Success }
import akka.actor.{Actor, ActorLogging, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import shk.api.rsvp.business._

class SendCampaign extends Actor with ActorLogging {

  import context.dispatcher

  def receive: Receive = {
    case CampaignPostRequest(campaignName, campaignVars) ⇒
      log.info("Received CampaignPostRequest message")
      val sendActor = sender()
      val mapVars = Map(Constants.APP_NAME → Constants.RSVP, Constants.CAMPAIGN_NAME → campaignName, Constants.CAMPAIGN_VARS → campaignVars)
      val httpRequest = HttpRequest(
        uri = Config.notifyUrl,
        method = HttpMethods.POST,
        entity = FormData(mapVars).toEntity(HttpCharsets.`UTF-8`)
      )
      Http(context.system).singleRequest(httpRequest)
    case _ ⇒
      sender() ! ResponseFailure("Request type is not handled...")
  }
}

object SendCampaign {
  def props: Props = {
    Props(classOf[SendCampaign])
  }
}
