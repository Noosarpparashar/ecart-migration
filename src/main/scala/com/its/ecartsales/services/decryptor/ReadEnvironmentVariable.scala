package com.its.ecartsales.services.decryptor

import com.its.ecartsales.framework.jobs.Job
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.services.decryptor.GetPasswordFromSecretManager._

class ReadEnvironmentVariable (spark: SparkSession) extends Job{

   lazy val secretId = "prod/XYZ_DATABASE"
   lazy val CUSTOMER_MASTER_KEY =sys.env("CUSTOMER_MASTER_KEY")
   lazy val myDbPasswd = getAndDecryptSecret(secretId,CUSTOMER_MASTER_KEY)
  println(myDbPasswd)

}
