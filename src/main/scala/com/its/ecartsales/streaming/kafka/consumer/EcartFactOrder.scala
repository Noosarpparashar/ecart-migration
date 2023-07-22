package com.its.ecartsales.streaming.kafka.consumer
//import com.amazonaws.thirdparty.joda.time.format.ISODateTimeFormat.time
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.framework.jobs.{Job, JobEnum}

import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import org.apache.spark.sql.functions.{col, from_json, json_tuple, timestamp_seconds}

class EcartFactOrder(spark: SparkSession) extends Job {
  import spark.implicits._

  val kafkaBootstrapServers = "http://34.16.170.59:9092,http://34.16.170.59:9093,http://34.16.170.59:9094"

  val topic = "mysixthtopic.ecart.fact_order"
  val startingOffsets = "earliest"
  val checkpointLocation = "s3a://ecart-sales-datalake/checkpoint_test_topic9/"
  val path = "s3a://ecart-sales-datalake/temp-test9/"

  val dateFormat = "yyyyMMdd_HHmmss"
  private val dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
  println("***********************************************",util.Properties.versionString,LocalDateTime.now())
  println("***********************************************",Runtime.version().version())


  val kafkaDF = spark.readStream .format("kafka").option("kafka.bootstrap.servers", kafkaBootstrapServers) .option("subscribe", topic).load()
  //.option("startingOffsets", startingOffsets)
   // .load()


  val jsonParsedDF = kafkaDF
    .selectExpr("CAST(value as STRING)")
    .as[String]
    .select(json_tuple(col("value"), "schema", "payload"))
    .toDF("schema", "payload")
    .select(json_tuple(col("payload"), "after", "op", "source", "ts_ms"))
    .toDF("after", "op", "source", "ts_ms")
    .select(col("op"), col("after"), col("ts_ms"), json_tuple(col("source"), "lsn"))
    .toDF("op", "after", "ts_ms", "lsn")
    .select(col("op"), col("lsn"), col("ts_ms"),
      json_tuple(col("after"), "orderid", "custid", "productid", "purchasetimestamp"))
    .toDF("op", "lsn", "ts_ms", "orderid", "custid", "productid", "purchasetimestamp")

  jsonParsedDF.writeStream
    .format("parquet")
    .outputMode("append")
    .option("checkpointLocation", checkpointLocation)
    .option("path", path + LocalDateTime.now().format(dateTimeFormatter))
    .start()
    .awaitTermination()
  logger.info("Hi I am from logger2")

}
