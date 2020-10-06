import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

enablePlugins(ScalaJSPlugin)

val ScalaTestVersion = "3.2.1"

lazy val scala212 = "2.12.10"
lazy val scala213 = "2.13.1" // 2.13.3 is not supported with Scala.js 0.6.x
lazy val supportedScalaVersions = List(scala212, scala213)

traceLevel        in ThisBuild := 0
githubOwner       in ThisBuild := "orbeon"
githubRepository  in ThisBuild := "sax"
githubTokenSource in ThisBuild := TokenSource.Environment("GITHUB_TOKEN")

publish / skip := true

lazy val DebugTest = config("debug-test") extend Test

lazy val sax = (crossProject(JVMPlatform, JSPlatform).crossType(CrossType.Pure) in file("."))
  .settings(
    organization := "org.xml",
    name         := "sax",
    version      := "2.0.2-SNAPSHOT",

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
  .configs(DebugTest)
  .jvmSettings(
    fork              in DebugTest     := true, // "By default, tests executed in a forked JVM are executed sequentially"
    sourceDirectory   in DebugTest     := (sourceDirectory in Test).value,
    javaOptions       in DebugTest     += "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005",
    parallelExecution in DebugTest     := false
  )

lazy val saxJS  = sax.js
lazy val saxJVM = sax.jvm.configs(DebugTest)
