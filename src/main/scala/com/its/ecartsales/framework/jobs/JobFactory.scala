package com.its.ecartsales.framework.jobs

import com.its.ecartsales.streaming.kafka.consumer.EcartFactOrder
import com.its.ecartsales.services.decryptor.ReadEnvironmentVariable
import org.apache.spark.sql.SparkSession



object JobFactory {
  def createJob(spark: SparkSession, jobType: JobEnum.JobEnum): Job = {
    jobType match {
      case JobEnum.EcartFactOrder =>
        new EcartFactOrder(spark)
      case JobEnum.ReadEnvironmentVariable =>
        new ReadEnvironmentVariable(spark)


//      case JobEnum.OtherJob =>
//        new OtherJob(spark)
      // Add more cases for additional job types

      case _ =>
        throw new IllegalArgumentException("Invalid job type")
    }
  }

}
