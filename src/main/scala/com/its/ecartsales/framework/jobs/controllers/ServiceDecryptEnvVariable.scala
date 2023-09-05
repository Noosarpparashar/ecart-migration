package com.its.ecartsales.framework.jobs.controllers
import com.its.ecartsales.framework.jobs.{Job, JobEnum, JobFactory}


object ServiceDecryptEnvVariable extends BaseController with Job {
  def main(args: Array[String]): Unit = {

    val classpath = System.getProperty("java.class.path")
    println("Classpath:")
    println("******************************************", classpath)


    /*
    * You can change below configurations if required
    * 1. spark configurations
    * 2. access key
    * 3. secret key
    *
    *
      val spark: SparkSession = SparkSession.builder()
       w    .master("local[1]")
          .appName("SparkByExamples3.com")
          .getOrCreate()
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.access.key", accessKey)
    spark.sparkContext
      .hadoopConfiguration.set("fs.s3a.secret.key", secretKey)

       */

    val jobType = JobEnum.ReadEnvironmentVariable
    JobFactory.createJob(spark, jobType)
    println("Hello from",jobType )
    spark.stop()
    /*


    * */

  }


}
