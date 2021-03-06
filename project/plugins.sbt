addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject"      % "1.0.0")
addSbtPlugin("org.scala-js"       % "sbt-scalajs"                   % "1.2.0")
addSbtPlugin("org.portable-scala" % "sbt-scala-native-crossproject" % "1.0.0")
addSbtPlugin("org.scala-native"   % "sbt-scala-native"              % "0.4.0")
addSbtPlugin("com.codecommit"     % "sbt-github-packages"           % "0.5.2")

resolvers += Resolver.file("ivy-local", file("ivy-local"))(Resolver.ivyStylePatterns)
