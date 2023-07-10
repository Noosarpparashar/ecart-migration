package com.its.ecartsales.framework.jobs.controllers
import com.its.ecartsales.framework.jobs.{Job, JobEnum, JobFactory}
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.streaming.kafka.consumer.EcartFactOrder


object StreamKafkaConsumerEcartFactOrder1  {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]").appName("SparkByExamples3.com")
      .getOrCreate()
    val jobType = JobEnum.KafkaConsumerEcartFactOrder
    val job = JobFactory.createJob(spark, jobType)
    println("Hello from controller1")
    //job.execute()

    spark.stop()
    //val customerTransform1 = new EcartFactOrder(spark: SparkSession)

  }


}
