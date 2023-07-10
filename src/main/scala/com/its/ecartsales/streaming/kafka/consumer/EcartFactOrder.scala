package com.its.ecartsales.streaming.kafka.consumer
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.framework.jobs.{JobEnum, Job}

class EcartFactOrder(spark: SparkSession) extends Job {
  import spark.implicits._

println("Hello from outside execute")
  logger.info("Hi I am from logger")
}
