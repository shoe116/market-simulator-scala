import sbt._

name := "buffett-code-market-simulator"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  // utils
  "joda-time" % "joda-time" % "2.10.5",
  // test
  "org.scalactic" %% "scalactic" % "3.1.0",
  "org.scalatest" %% "scalatest" % "3.1.0" % Test
)

// append fmt and check command to keep scala format clean
addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
