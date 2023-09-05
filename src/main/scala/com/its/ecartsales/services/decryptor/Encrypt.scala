package com.its.ecartsales.services.decryptor

import com.amazonaws.regions.Regions
import com.amazonaws.services.kms.AWSKMSClientBuilder
import org.apache.spark.sql.SparkSession

import java.nio.ByteBuffer

object Encrypt extends App {
  // Initialize SparkSession (eagerly)
  val spark: SparkSession = SparkSession.builder()
    .master("local[1]")
    .appName("EcartSalesEncryptor" +
      "")
    .getOrCreate()

  // Initialize AWS KMS client (eagerly)
  val kmsClient = AWSKMSClientBuilder.standard()
    .withRegion(Regions.US_EAST_1)
    .build()

  // Define plaintext data (eagerly)
  val plaintextData = "Prasoon@123"
  val plaintextByteBuffer = ByteBuffer.wrap(plaintextData.getBytes())

  // Define a lazy val for customer master key
  lazy val customerMasterKey: String = sys.env.getOrElse("CUSTOMER_MASTER_KEY", "")

  // Encrypt using AWS KMS
  val encryptRequest = new com.amazonaws.services.kms.model.EncryptRequest()
    .withKeyId(customerMasterKey)
    .withPlaintext(plaintextByteBuffer)

  val encryptResponse = kmsClient.encrypt(encryptRequest)
  val encryptedData = encryptResponse.getCiphertextBlob.array()

  // Convert encrypted data to Base64-encoded string (eagerly)
  val base64EncryptedData: String = java.util.Base64.getEncoder.encodeToString(encryptedData)

  // Print the Base64-encoded string
  println(s"Encrypted Data (Base64): $base64EncryptedData")

  // Perform Spark operations with the encrypted data (replace with actual operations)
  // For example:
  // val encryptedDataFrame = spark.read.textFile(base64EncryptedData)
  // encryptedDataFrame.show()

  // Stop the SparkSession when done
  spark.stop()
}
