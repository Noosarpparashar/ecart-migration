package com.its.ecartsales.streaming.kafka.consumer

//import com.amazonaws.auth.{AWSStaticCredentialsProvider, BasicAWSCredentials}
//import com.amazonaws.client.builder.AwsClientBuilder
//import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.kms.AWSKMSClientBuilder
//import com.amazonaws.services.kms.model.{DescribeKeyRequest, EncryptRequest}
//import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest
//import com.amazonaws.services.secretsmanager.{AWSSecretsManager, AWSSecretsManagerClientBuilder}
import org.apache.spark.sql.SparkSession
import org.json4s.DefaultFormats
import java.util.Base64

import java.nio.ByteBuffer


object EncryptDecrypt extends App{
  val spark: SparkSession = SparkSession.builder()
    .master("local[1]").appName("SparkByExamples3.com")
    .getOrCreate()
val kmsClient = AWSKMSClientBuilder.standard()
  .withRegion(Regions.US_EAST_1)
  .build()
  // Create plaintext data
   val plaintextData = "MySecretData"
  val plaintextByteBuffer = ByteBuffer.wrap(plaintextData.getBytes())

  // Encrypt using AWS KMS
  println("This is from environemnt variable",sys.env("CUSTOMER_MASTER_KEY"))
  val encryptRequest = new com.amazonaws.services.kms.model.EncryptRequest()
    .withKeyId(sys.env("CUSTOMER_MASTER_KEY"))
    .withPlaintext(plaintextByteBuffer)



  val encryptResponse = kmsClient.encrypt(encryptRequest)
  val encryptedData = encryptResponse.getCiphertextBlob.array()

  //println("Encrypted Data: " + encryptedData.mkString(", "))
  println("Encrypted Data: " + encryptedData)
  val base64EncryptedData: String = java.util.Base64.getEncoder.encodeToString(encryptedData)

  // Print the Base64-encoded string
  println(s"Encrypted Data (Base64): $base64EncryptedData")


  //   Perform Spark operations with the encrypted data
//   For example:
 println("THis is encrypted data", encryptedData.getClass.getSimpleName,encryptedData)

  //val encryptedData: Array[Byte] = encryptedData

  // Decrypt using AWS KMS
  val decryptRequest = new com.amazonaws.services.kms.model.DecryptRequest()
    .withCiphertextBlob(ByteBuffer.wrap(encryptedData))

  val decryptResponse = kmsClient.decrypt(decryptRequest)
  val decryptedData = decryptResponse.getPlaintext.array()

  // Perform Spark operations with the decrypted data
  // For example:
  val decryptedString = new String(decryptedData)
  println("Decrypted Data: " + decryptedString)

  println("*************************************************************************")

  println("Enter the Base64-encoded encrypted data:")
  val inputBase64EncryptedData = scala.io.StdIn.readLine()

  // Decode the Base64-encoded string to a byte array
  val encryptedData1: Array[Byte] = Base64.getDecoder.decode(inputBase64EncryptedData)

  // Decrypt using AWS KMS
  val decryptRequest1 = new com.amazonaws.services.kms.model.DecryptRequest()
    .withKeyId("d155117f-0930-48bf-b827-c87a02eeaac3")
    .withCiphertextBlob(ByteBuffer.wrap(encryptedData1))

  val decryptResponse1 = kmsClient.decrypt(decryptRequest1)
  val decryptedData1 = decryptResponse1.getPlaintext.array()

  // Display the decrypted data
  val decryptedString1 = new String(decryptedData1)
  println("Decrypted Data: " + decryptedString1)
/*
AQICAHier+0NUy1Cw60k0pD81r41+TJGLCYqm/5nARn7ReNsNwFMJp+jKGmKiKM/QjEkA7aEAAAAajBoBgkqhkiG9w0BBwagWzBZAgEAMFQGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQM0AQConi4JuQTclVnAgEQgCfs///O1IMWLhHJMPWPXFNwtkHQd00lRBAV5Uk9HKyt/wpjQSjzKdI=
* */

}
