package com.its.ecartsales.framework.util.s3
import com.typesafe.config.ConfigFactory


class S3Reader {
  private val config = ConfigFactory.load("com/its/ecartsales/configs/dev/s3.conf")
  private val credentials = config.getConfig("credentials").getConfig("s3")
  private val set1Credentials = credentials.getConfig("startup-datalake1-rw")
  private val accessKey = set1Credentials.getString("accessKey")
  private val secretKey = set1Credentials.getString("secretKey")
  private val region = set1Credentials.getString("region")
  private val s3Connection = new S3Connection(accessKey, secretKey, region)
  private val s3Client = s3Connection.getS3Client
  private val bucketName = "startup-datalake1"

  private val objectListing = s3Client.listObjects(bucketName)
  val objectSummaries = objectListing.getObjectSummaries

}
