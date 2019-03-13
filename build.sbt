name := "ignite-scala-sample-code"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "org.apache.ignite" % "ignite-core" % "2.6.0",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.scalactic" %% "scalactic" % "3.0.5"
)