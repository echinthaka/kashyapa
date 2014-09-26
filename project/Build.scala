import sbt._

import scalabuff.ScalaBuffPlugin._

object build extends Build {
  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Project.defaultSettings ++ scalabuffSettings).configs(ScalaBuff)
}
