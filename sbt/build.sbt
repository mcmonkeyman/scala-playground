name := """test"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++=  Seq(
  "com.github.pathikrit" % "better-files_2.11" % "2.15.0",
  "com.lihaoyi" %% "ammonite-ops" % "0.5.7",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
//
initialCommands in console := """
    |import better.files._
    |import scala.sys.process._
    |import scala.util.Random
    |import ammonite.ops._
    |import java.io.{File => JFile}
    |import ammonite.ops.ImplicitWd._
    |""".stripMargin
