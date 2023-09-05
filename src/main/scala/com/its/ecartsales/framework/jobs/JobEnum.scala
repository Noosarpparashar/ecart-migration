package com.its.ecartsales.framework.jobs

object JobEnum extends Enumeration {
  type JobEnum = Value

  /*
  List of Streaming Jobs used in the project
  */
  val EcartFactOrder: Value = Value("EcartFactOrder")
  val ReadEnvironmentVariable: Value = Value("ReadEnvironmentVariable")
  val OtherJob: Value = Value("OtherJob")
//  val Job2: Value = Value("Job2")
//  val Job3: Value = Value("Job3")

  /*
  List of Batch Jobs used in the project
  */
//  val Job4: Value = Value("Job4")
//  val Job5: Value = Value("Job5")
//  val Job6: Value = Value("Job6")

  def getJobName(job: JobEnum): String = job.toString
}