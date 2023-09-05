package com.its.ecartsales.framework.jobs.controllers

import com.typesafe.config.ConfigFactory
import org.apache.spark.sql.SparkSession
class BaseController {

  val spark: SparkSession = SparkSession.builder()
    .master("local[1]").appName("SparkByExamples3.com")
    .getOrCreate()

  val classpath = System.getProperty("java.class.path")
  println("Classpath:")
  println("******************************************", classpath)
  private val config = ConfigFactory.load("configs/dev/s3.conf")

  private val s3CredentialsConfig = config.getConfig("credentials").getConfig("s3")
  private val s3BucketConfig = s3CredentialsConfig.getConfig("startup-datalake1-rw")
  private val accessKey = s3BucketConfig.getString("accessKey")
  private val secretKey = s3BucketConfig.getString("secretKey")
  private val region = s3BucketConfig.getString("region")


  spark.sparkContext.setLogLevel("ERROR")
  spark.sparkContext
    .hadoopConfiguration.set("fs.s3a.access.key", accessKey)
  spark.sparkContext
    .hadoopConfiguration.set("fs.s3a.secret.key", secretKey)
  spark.sparkContext
    .hadoopConfiguration.set("fs.s3a.endpoint", "s3.amazonaws.com")

}
