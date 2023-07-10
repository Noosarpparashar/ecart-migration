package com.its.ecartsales.framework.jobs
import org.apache.logging.log4j.{LogManager,Logger}
import org.apache.spark.sql.SparkSession
trait Job {
  protected val logger:Logger = LogManager.getLogger(this.getClass.getName)

}
