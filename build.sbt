lazy val root = (project in file(".")).
  settings(
    name := "shk-rsvp-api",
    organization := "shk.api.rsvp",
    version := "0.1.3",
    scalaVersion := "2.12.6"
  )

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.akka" %% "akka-http"   % "10.1.0",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.0",
  "com.typesafe.akka" %% "akka-stream" % "2.5.17",
  "com.typesafe.akka" %% "akka-actor" % "2.5.17",
  "com.typesafe.akka" %% "akka-slf4j" % "2.5.17",
  "ch.megard" %% "akka-http-cors" % "0.3.0",
  "net.debasishg" %% "redisclient" % "3.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)
libraryDependencies += "shk.data.rsvp" %% "shk-rsvp-data" % "0.1.9"
libraryDependencies += "shk.utils" %% "shk-utils" % "0.2.3"

// https://www.scala-sbt.org/sbt-native-packager/gettingstarted.html#packaging-formats
enablePlugins(JavaAppPackaging)
