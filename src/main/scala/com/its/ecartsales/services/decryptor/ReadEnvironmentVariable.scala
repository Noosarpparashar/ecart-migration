package com.its.ecartsales.services.decryptor

import com.its.ecartsales.framework.jobs.Job
import org.apache.spark.sql.SparkSession

class ReadEnvironmentVariable (spark: SparkSession) extends Job{
  println("Hellloooooo")
  println(sys.env("CUSTOMER_MASTER_KEY"))

}
