package shk.api.rsvp.business

import com.typesafe.config.ConfigFactory

object Config {

  val conf = ConfigFactory.load()

  val currentEnv = conf.getString(Constants.ENV)

  val httpHost = conf.getString(Constants.HTTP_HOST)
  val httpPort = conf.getInt(Constants.HTTP_PORT)
  val actorTimeout = conf.getInt(Constants.ACTOR_TIMEOUT)
  val actorPool = conf.getInt(Constants.ACTOR_POOL)
  val redisHost = conf.getString(Constants.REDIS_HOST)
  val redisPort = conf.getInt(Constants.REDIS_PORT)
  val notifyUrl = conf.getString(Constants.NOTIFY_URL)

}
