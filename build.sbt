import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaNativePlugin)

val scala213 = "2.13.15"
val supportedScalaVersions = List(scala213)

ThisBuild / scalaVersion := scala213

ThisBuild / traceLevel        := 0
ThisBuild / githubOwner       := "orbeon"
ThisBuild / githubRepository  := "sax"
ThisBuild / githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")

lazy val sax = (crossProject(JVMPlatform, JSPlatform, NativePlatform).crossType(CrossType.Pure) in file("."))
  .settings(
    organization := "org.xml",
    name         := "sax",
    version      := "2.0.2.9-SNAPSHOT",

    scalaVersion       := scala213,
    crossScalaVersions := supportedScalaVersions,

    scalacOptions ++= Seq(
      "-encoding", "utf8",
      "-deprecation",
      "-unchecked"
    ),

    Compile / javaSource  := baseDirectory.value / "dummy",
    Test    / javaSource  := baseDirectory.value / "dummy"
  )

lazy val saxJS  = sax.js
lazy val saxJVM = sax.jvm
lazy val saxNative = sax.native

lazy val root = project.in(file("."))
  .aggregate(saxJS, saxJVM, saxNative)
  .settings(
    publish := {},
    publishLocal := {},
    ThisProject / sourceDirectory := baseDirectory.value / "root",
    crossScalaVersions            := Nil // "crossScalaVersions must be set to Nil on the aggregating project"
  )
