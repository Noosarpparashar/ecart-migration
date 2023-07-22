package com.its.ecartsales.framework.util.s3

import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.{AmazonS3, AmazonS3ClientBuilder}
import com.typesafe.config.ConfigFactory

class S3Connection(accessKey: String, secretKey: String, region: String) {

  private val credentials = new BasicAWSCredentials(accessKey, secretKey)
  private val endpoint = new EndpointConfiguration(s"s3.$region.amazonaws.com", region)

  // Create a new instance of AmazonS3ClientBuilder
  private val s3ClientBuilder = AmazonS3ClientBuilder.standard()
    .withCredentials(new AWSStaticCredentialsProvider(credentials))
    .withEndpointConfiguration(endpoint)

  // Build the AmazonS3 client
  private val s3Client: AmazonS3 = s3ClientBuilder.build()

  def getS3Client: AmazonS3 = s3Client
}