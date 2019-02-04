package shk.api.rsvp.business

import shk.data.rsvp.business.{ Guest }

// generics classes ??

sealed abstract class HttpRequest
sealed abstract class HttpResponse

final case class ResponseFailure(errMessage: String) extends HttpResponse
final case class ResponseSuccess(resp: String) extends HttpResponse

// specific classes

final case class GuestPostRequest(gust: Guest) extends HttpRequest
final case class GuestResponseSuccess(resp: Guest) extends HttpResponse

final case class CampaignPostRequest(campaignName: String, campaignVars: String)
