package com.its.ecartsales.services.decryptor

import com.its.ecartsales.framework.jobs.Job
import org.apache.spark.sql.SparkSession

class ReadEnvironmentVariable (spark: SparkSession) extends Job{
  println("Hellloooooo")
  println("I am from line 8")
  println("I am from system variable",sys.env("CUSTOMER_MASTER_KEY"))

}
