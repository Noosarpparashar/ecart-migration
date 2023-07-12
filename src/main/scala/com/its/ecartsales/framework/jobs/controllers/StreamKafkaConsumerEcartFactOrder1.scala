package com.its.ecartsales.framework.jobs.controllers
import com.its.ecartsales.framework.jobs.{Job, JobEnum, JobFactory}
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.streaming.kafka.consumer.EcartFactOrder


object StreamKafkaConsumerEcartFactOrder1  {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .master("local[1]").appName("SparkByExamples3.com")
      .getOrCreate()
    spark.sparkContext.setLogLevel("ERROR")
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.access.key", "ABCDEFGHIJKL")
    // Replace Key with your AWS secret key (You can find this on IAM
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.secret.key", "12345")
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.endpoint", "s3.amazonaws.com")
    val jobType = JobEnum.EcartFactOrder
    val job = JobFactory.createJob(spark, jobType)
    println("Hello from controller1")
    //job.execute()
//    val accessKey = "AKIAQQFHLXYN4V23EFPM"
//    val secretKey = "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"
//val customerTransform1 = new EcartFactOrder(spark: SparkSession)

    spark.stop()
    //

  }


}
