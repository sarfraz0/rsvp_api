package shk.api.rsvp.business

object Constants {

  // Fields constants
  val ENV = "env"
  val AUTH = "auth"
  val HOST = "host"
  val PORT = "port"
  val HTTP = "http"
  val APP = "app"
  val ACTOR = "actor"
  val TIMEOUT = "timeout"
  val POOL = "pool"
  val REDIS = "redis"
  val MIDDLEWARES = "middlewares"
  val NOTIFY_API = "notify_api"
  // routes
  val GUESTS = "guests"

  // Configuration constants
  val HTTP_HOST = s"$HTTP.$HOST"
  val HTTP_PORT = s"$HTTP.$PORT"
  val ACTOR_TIMEOUT = s"$APP.${ACTOR}_${TIMEOUT}"
  val ACTOR_POOL = s"$APP.${ACTOR}_${POOL}"
  val REDIS_HOST = s"$REDIS.$HOST"
  val REDIS_PORT = s"$REDIS.$PORT"

  // app
  val RSVP = "rsvp"

  // notify integration
  val CAMPAIGNS = "campaigns"
  val APP_NAME = "app_name"
  val CAMPAIGN_NAME = "campaign_name"
  val CAMPAIGN_VARS = "campaign_vars"
  val NEW_JOIN = "NEW_JOIN"

  val NOTIFY_URL = s"$MIDDLEWARES.$NOTIFY_API"

}
