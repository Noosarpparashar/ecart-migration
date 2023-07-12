package com.its.ecartsales.framework.jobs

import com.its.ecartsales.streaming.kafka.consumer.EcartFactOrder
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.framework.jobs.{JobEnum, Job}


object JobFactory {
  def createJob(spark: SparkSession, jobType: JobEnum.JobEnum): Job = {
    jobType match {
      case JobEnum.EcartFactOrder =>
        new EcartFactOrder(spark)


//      case JobEnum.OtherJob =>
//        new OtherJob(spark)
      // Add more cases for additional job types

      case _ =>
        throw new IllegalArgumentException("Invalid job type")
    }
  }

}
