package com.its.ecartsales.services.decryptor

import com.amazonaws.regions.Regions
import com.amazonaws.services.secretsmanager.model.{GetSecretValueRequest, GetSecretValueResult}
import com.amazonaws.services.secretsmanager.{AWSSecretsManager, AWSSecretsManagerClient, AWSSecretsManagerClientBuilder}
import com.amazonaws.thirdparty.jackson.databind.ObjectMapper
import com.amazonaws.regions.Regions
import com.amazonaws.services.kms.AWSKMSClientBuilder
import org.apache.spark.sql.SparkSession
import java.util.Base64

import java.nio.ByteBuffer

object GetPasswordFromSecretManager extends App {

 val region = Regions.US_EAST_1


 val client: AWSSecretsManager = AWSSecretsManagerClientBuilder.standard()
   .withRegion(region)
   .build()
 val getSecretValueRequest = new GetSecretValueRequest().withSecretId("dev/XYZ_DATABASE")
 val getSecretValueResult: GetSecretValueResult = client.getSecretValue(getSecretValueRequest)

 println("Secret retrieved ")
 val secretBinaryString = getSecretValueResult.getSecretString
 print(secretBinaryString)



 val objectMapper = new ObjectMapper()

 val secretMap = objectMapper.readValue(secretBinaryString, classOf[java.util.HashMap[String, String]])


 val inputBase64EncryptedData= secretMap.get("XYZ_DATABASE_PASSWORD")
 //println("Secret password = " + secretMap.get("password"))
 val kmsClient = AWSKMSClientBuilder.standard()
   .withRegion(Regions.US_EAST_1)
   .build()


 println("Enter the Base64-encoded encrypted data:")
// val inputBase64EncryptedData = scala.io.StdIn.readLine()

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

// val url = s"jdbc:postgresql://${secretMap.get("host")}:${secretMap.get("port")}/dbName"
// println("Secret url = " + url)
// println("Secret username = " + secretMap.get("username"))
// println("Secret password = " + secretMap.get("password"))
}
