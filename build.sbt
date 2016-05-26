name := "scala-study"

version := "1.0"

scalaVersion := "2.11.7"

sbtVersion := "0.13.0"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += {
  "com.github.nscala-time" %% "nscala-time" % "2.12.0"
}

libraryDependencies += {
  "com.typesafe.akka" %% "akka-actor" % "2.4.4"
}

libraryDependencies += {
  "io.netty" % "netty-all" % "4.1.0.Final"
}

libraryDependencies += {
  "org.slf4j" % "slf4j-api" % "1.7.21"
}
