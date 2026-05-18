import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

enablePlugins(ScalaJSPlugin)
enablePlugins(ScalaNativePlugin)

val scala213 = "2.13.18"
val scala3   = "3.7.4"
val mainScalaVersion       = scala213

val supportedScalaVersions = List(scala213, scala3)

ThisBuild / scalaVersion := mainScalaVersion

ThisBuild / traceLevel        := 0
ThisBuild / githubOwner       := "orbeon"
ThisBuild / githubRepository  := "sax"
ThisBuild / githubTokenSource := TokenSource.Environment("GITHUB_TOKEN")

lazy val sax = (crossProject(JVMPlatform, JSPlatform, NativePlatform).crossType(CrossType.Pure) in file("."))
//lazy val sax = (crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Pure) in file("."))
  .settings(
    organization := "org.xml",
    name         := "sax",
    version      := "2.0.2.10-SNAPSHOT",

    scalaVersion       := mainScalaVersion,
    crossScalaVersions := supportedScalaVersions,

    scalacOptions ++= Seq(
      "-encoding", "utf8",
      "-deprecation",
      "-unchecked",
      "-Xsource-features:v2.13.14",
    ),

    Compile / javaSource  := baseDirectory.value / "dummy",
    Test    / javaSource  := baseDirectory.value / "dummy"
  )

lazy val saxJS  = sax.js
lazy val saxJVM = sax.jvm
lazy val saxNative = sax.native

lazy val root = project.in(file("."))
  .aggregate(saxJS, saxJVM, saxNative)
//  .aggregate(saxJS, saxJVM)
  .settings(
    publish := {},
    publishLocal := {},
    ThisProject / sourceDirectory := baseDirectory.value / "root",
    crossScalaVersions            := Nil // "crossScalaVersions must be set to Nil on the aggregating project"
  )
