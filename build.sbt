ThisBuild / version := "0.1.0-SNAPSHOT"

//ThisBuild / scalaVersion := "2.13.11"
ThisBuild / scalaVersion := "2.12.15"
val sparkVersion = "3.3.2"

val dependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion ,
  "org.apache.spark" %% "spark-sql" % sparkVersion ,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
 // "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion ,
  "org.apache.spark" %% "spark-sql-kafka-0-10" % sparkVersion,
  "org.apache.hadoop" % "hadoop-aws" % "3.3.1",
  // https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-common
  "org.apache.hadoop" % "hadoop-common" % "3.3.1",
  // https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
  "org.apache.hadoop" % "hadoop-client" % "3.3.1" % "provided",
  "org.postgresql" % "postgresql" % "42.5.0",
  "com.amazonaws" % "aws-java-sdk-kms" % "1.12.66",
  "com.typesafe" % "config" % "1.4.2",





)
libraryDependencies ++=dependencies
 //sbt-assembly settings
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
//ThisBuild / assemblyMergeStrategy := {
//  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//  case x => MergeStrategy.first
//}



lazy val root = (project in file("."))
  .settings(
    name := "ecart-migration",
//    ThisBuild / assemblyMergeStrategy := {
//      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
//      case x => MergeStrategy.first
//    }

  )
