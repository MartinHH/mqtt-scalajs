ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.martinhh"
ThisBuild / organizationName := "io.github.martinhh"

ThisBuild / scalaVersion := "3.3.1"

publish / skip := true

lazy val mqttScalaJs = (project in file("mqtt-scalajs"))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "mqtt-scalajs"
  )

// tests are kept in a different module for stronger separation of configurations
lazy val tests = (project in file("tests"))
  .dependsOn(mqttScalaJs)
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    name := "tests",
    libraryDependencies += "org.scalameta" %%% "munit" % "1.0.0" % Test,
    Test / npmDependencies += "mqtt" -> "5.6.1",
    webpack / version := "5.91.0",
    startWebpackDevServer / version := "5.0.4",
    publish / skip := true
  )
