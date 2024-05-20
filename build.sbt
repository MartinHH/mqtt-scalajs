ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "io.github.martinhh"
ThisBuild / organizationName := "io.github.martinhh"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "mqtt-scalajs"
  )
