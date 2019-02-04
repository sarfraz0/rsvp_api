package shk.api.rsvp.actors

import akka.actor.{Actor, ActorLogging, Props}
import shk.data.rsvp.services.RsvpServiceImpl
import shk.api.rsvp.business._

class GuestHandler extends Actor with ActorLogging {

  import context.dispatcher

  def receive: Receive = {
    case GuestPostRequest(gust) ⇒
      log.info("Received GuestPostRequest message")
      val sendActor = sender()
      RsvpServiceImpl.rsvpService.postGuest(gust).map { reqStat ⇒
        if (reqStat.isDefined) {
          sendActor ! GuestResponseSuccess(reqStat.get)
        } else {
          sendActor ! ResponseFailure("Could not insert entity in DB")
        }
      }
    case _ ⇒
      sender() ! ResponseFailure("Request type is not handled...")
  }
}

object GuestHandler {
  def props: Props = {
    Props(classOf[GuestHandler])
  }
}
