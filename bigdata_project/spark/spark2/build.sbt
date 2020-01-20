name := "spark2"

version := "0.1"

scalaVersion := "2.11.8"

//libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.1.0"
)