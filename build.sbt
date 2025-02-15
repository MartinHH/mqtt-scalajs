ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.martinhh"
ThisBuild / organizationName := "io.github.martinhh"

ThisBuild / scalaVersion := "3.3.1"

publish / skip := true

val mUnitVersion = "1.1.0"

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
    libraryDependencies ++= Seq(
      "org.scalameta" %%% "munit" % mUnitVersion % Test,
      "org.scalameta" %%% "munit-scalacheck" % mUnitVersion % Test,
      "io.github.martinhh" %%% "scalacheck-derived-extras" % "0.6.0" % Test,
      // used as reference implementation for Buffer facade:
      ("org.scommons.nodejs" %%% "scommons-nodejs-core" % "1.0.0").cross(CrossVersion.for3Use2_13) % Test
    ),
    Test / npmDependencies += "mqtt" -> "5.6.1",
    webpack / version := "5.91.0",
    startWebpackDevServer / version := "5.0.4",
    publish / skip := true
  )
