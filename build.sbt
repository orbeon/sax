import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

enablePlugins(ScalaJSPlugin)

val ScalaTestVersion = "3.2.1"

val scala212 = "2.12.12"
val scala213 = "2.13.3"
val supportedScalaVersions = List(scala212, scala213)

ThisBuild / scalaVersion := scala213

traceLevel        in ThisBuild := 0
githubOwner       in ThisBuild := "orbeon"
githubRepository  in ThisBuild := "sax"
githubTokenSource in ThisBuild := TokenSource.Environment("GITHUB_TOKEN")

lazy val sax = (crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Pure) in file("."))
  .settings(
    organization := "org.xml",
    name         := "sax",
    version      := "2.0.2.2-SNAPSHOT",

    scalaVersion       := scala213,
    crossScalaVersions := supportedScalaVersions,

    scalacOptions ++= Seq(
      "-encoding", "utf8",
      "-deprecation",
      "-unchecked"
    ),

    libraryDependencies += "org.scalactic" %%% "scalactic"     % ScalaTestVersion    % Test,
    libraryDependencies += "org.scalatest" %%% "scalatest"     % ScalaTestVersion    % Test,

    testOptions       in Test          += Tests.Argument(TestFrameworks.ScalaTest, "-oF"),

    javaSource in Compile  := baseDirectory.value / "dummy",
    javaSource in Test     := baseDirectory.value / "dummy"
  )

lazy val saxJS  = sax.js
lazy val saxJVM = sax.jvm

lazy val root = project.in(file("."))
  .aggregate(saxJS, saxJVM)
  .settings(
    publish := {},
    publishLocal := {},
    ThisProject / sourceDirectory := baseDirectory.value / "root",
    crossScalaVersions            := Nil // "crossScalaVersions must be set to Nil on the aggregating project"
  )
