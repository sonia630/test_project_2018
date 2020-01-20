name := "scala_test"

version := "0.1"

scalaVersion := "2.12.6"

//libraryDependencies ++= {
//  val sparkVer = "2.0.2"
//  Seq(
//    "org.apache.spark" %% "spark-core" % sparkVer
//  )
//}

val sparkVersion = "2.3.0"

/*
resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)
*/
//libraryDependencies ++= Seq(
//  "org.apache.spark" % "spark-core_" % sparkVersion,
//  "org.apache.spark" % "spark-sql_" % sparkVersion,
//  "org.apache.spark" % "spark-streaming_" % sparkVersion,
//  "org.apache.spark" % "spark-mllib" % sparkVersion,
//  "org.apache.spark" % "spark-hive" % sparkVersion,
//  "mysql" % "mysql-connector-java" % "5.1.6"
//)



libraryDependencies ++= Seq(
    "org.apache.spark" % "spark-core" % sparkVersion,
  //  "org.apache.spark" % "spark-sql" % sparkVersion,
  //  "org.apache.spark" % "spark-streaming" % sparkVersion,
  "org.apache.spark" % "spark-core_2.10" % "1.6.0" exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy"),
  "org.apache.spark" % "spark-sql_2.10" % "1.6.0" exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy"),
//  "org.apache.hadoop" % "hadoop-common" % "2.7.0" exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy"),
  //  "org.apache.spark" % "spark-sql_2.10" % "1.6.0" exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy"),
//  "org.apache.spark" % "spark-hive_2.10" % "1.6.0" exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy"),
//  "org.apache.spark" % "spark-yarn_2.10" % "1.6.0" exclude("org.apache.hadoop", "hadoop-yarn-server-web-proxy"),
//  "com.github.scopt" %% "scopt" % "3.7.0"
)
