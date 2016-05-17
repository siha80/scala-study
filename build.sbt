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
