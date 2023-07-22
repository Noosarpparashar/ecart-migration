package com.its.ecartsales.framework.util.s3

object s3Caller extends App{

val s3obj = new S3Reader()
  val summaries = s3obj.objectSummaries
  println(summaries)


}
