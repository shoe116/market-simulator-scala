import sbt._

name := "buffett-code-market-simulator"

version := "0.1"

scalaVersion := "2.13.1"

// append fmt and check command to keep scala format clean
addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("check", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")
