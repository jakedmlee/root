ThisBuild / organization := "com.yeomokhwa"
ThisBuild / version := "0.1.0"
ThisBuild / scalaVersion := "2.13.5"
ThisBuild / crossScalaVersions := Seq("2.13.5", "3.0.0-RC3")

lazy val server = project
  .in(file("server"))
  .enablePlugins(PlayScala)
  .settings(
    name := "yeomokhwa-server",

    resolvers += Resolver.jcenterRepo,

    libraryDependencies ++= Seq (
      "com.novocode" % "junit-interface" % "0.11" % "test",
      ("org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test).cross(CrossVersion.for3Use2_13),
      ("net.codingwell" %% "scala-guice" % "5.0.0").cross(CrossVersion.for3Use2_13),
      "org.webjars" % "bootstrap" % "5.0.0",
      "org.postgresql" % "postgresql" % "42.2.20",
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
      "com.typesafe.play" %% "play-mailer" % "8.0.1",
      "com.typesafe.play" %% "play-mailer-guice" % "8.0.1",
      "com.adrianhurt" %% "play-bootstrap" % "1.6.1-P28-B4",
      "com.typesafe.play" %% "play-json" % "2.10.0-RC2",
      "com.mohiva" %% "play-silhouette" % "7.0.0",
      "com.mohiva" %% "play-silhouette-persistence" % "7.0.0",
      "com.mohiva" %% "play-silhouette-password-bcrypt" % "7.0.0",
      "com.mohiva" %% "play-silhouette-crypto-jca" % "7.0.0",
      "com.mohiva" %% "play-silhouette-testkit" % "7.0.0" % "test",
      "com.mohiva" %% "play-silhouette-cas" % "7.0.0",
      "com.mohiva" %% "play-silhouette-totp" % "7.0.0",
      "com.enragedginger" %% "akka-quartz-scheduler" % "1.9.1-akka-2.6.x",
      "com.iheart" %% "ficus" % "1.5.0",
      guice,
      ehcache,
      filters
    )

  )

lazy val root = project
  .in(file("."))
  .aggregate((server))

Global / onLoad := (Command.process("project server", _: State)) compose (Global / onLoad).value