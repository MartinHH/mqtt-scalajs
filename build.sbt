ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.martinhh"
ThisBuild / organizationName := "io.github.martinhh"

ThisBuild / scalaVersion := "3.3.3"

publish / skip := true

lazy val mqttScalaJs = (project in file("mqtt-scalajs"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    name := "mqtt-scalajs",
    libraryDependencies += "org.scalameta" %%% "munit" % "1.0.0-RC1" % Test,
    Test / npmDependencies += "mqtt" -> "5.6.1",
    webpack / version := "5.91.0",
    startWebpackDevServer / version := "5.0.4"
  )
