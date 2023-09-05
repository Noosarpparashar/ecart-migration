package com.its.ecartsales.services.decryptor


import com.amazonaws.regions.Regions
import com.amazonaws.services.kms.AWSKMSClientBuilder
import org.apache.spark.sql.SparkSession
import java.util.Base64

import java.nio.ByteBuffer


object Decrypt extends App{
  val spark: SparkSession = SparkSession.builder()
    .master("local[1]").appName("SparkByExamples3.com")
    .getOrCreate()

  val kmsClient = AWSKMSClientBuilder.standard()
    .withRegion(Regions.US_EAST_1)
    .build()


  println("Enter the Base64-encoded encrypted data:")
  val inputBase64EncryptedData = scala.io.StdIn.readLine()

  // Decode the Base64-encoded string to a byte array
  val encryptedData1: Array[Byte] = Base64.getDecoder.decode(inputBase64EncryptedData)

  // Decrypt using AWS KMS
  val decryptRequest1 = new com.amazonaws.services.kms.model.DecryptRequest()
    .withKeyId(sys.env("CUSTOMER_MASTER_KEY"))
    .withCiphertextBlob(ByteBuffer.wrap(encryptedData1))

  val decryptResponse1 = kmsClient.decrypt(decryptRequest1)
  val decryptedData1 = decryptResponse1.getPlaintext.array()

  // Display the decrypted data
  val decryptedString1 = new String(decryptedData1)
  println("Decrypted Data: " + decryptedString1)


}
