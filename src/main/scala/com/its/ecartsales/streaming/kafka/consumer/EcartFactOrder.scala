package com.its.ecartsales.streaming.kafka.consumer
import org.apache.spark.sql.SparkSession
import com.its.ecartsales.framework.jobs.{JobEnum, Job}
import java.time.format.DateTimeFormatter
import java.time.LocalDateTime
import org.apache.spark.sql.functions.{col, from_json, json_tuple}

class EcartFactOrder(spark: SparkSession) extends Job {
  import spark.implicits._

//  val columns = Seq("language", "users_count")
//  val data = Seq(("Java", "20000"), ("Python", "100000"), ("Scala", "3000"))
//
//  val rdd = spark.sparkContext.parallelize(data)
//
//  val dfFromRDD1 = rdd.toDF("language", "users_count")
//  dfFromRDD1.printSchema()
//  val format = "yyyyMMdd_HHmmss"
//  val dtf = DateTimeFormatter.ofPattern(format)
//  logger.info("Hi I am from logger")
//  println("Hi I am from logger")
//  dfFromRDD1.show()
//  dfFromRDD1.write
//    .parquet("s3a://startup-datalake/temp-test/" +LocalDateTime.now().format(dtf))



    val df =spark.readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094")
    .option("subscribe","mysecondtopic.ecart.fact_order")
    .option("startingOffsets","earliest")
    .load()
  val format = "yyyyMMdd_HHmmss"

  val dtf = DateTimeFormatter.ofPattern(format)
  val stringdf = df.selectExpr("CAST(value as STRING)").as[String]
    .select(json_tuple(col("value"), "schema", "payload")).toDF("schema", "payload")
    .select(json_tuple(col("payload"), "after", "op", "source", "ts_ms")).toDF("after", "op", "source", "ts_ms")
    .select(col("op"), col("after"),col("ts_ms"),
      json_tuple(col("source"), "lsn")).toDF( "op", "after", "ts_ms", "lsn")
    .select(col("op"), col("lsn"),col("ts_ms"),
       json_tuple(col("after"), "orderid", "custid", "productid", "purchasetimestamp"))
    .toDF("op", "lsn", "ts_ms","orderid", "custid", "productid", "purchasetimestamp")

  stringdf.writeStream
    .format("parquet")
         .format("console")
    //  .outputMode("update")
//    .option("checkpointLocation", "s3a://twitter-khyber/checkpoint/")
//    .option("path", "s3a://twitter-khyber/test-result/" + LocalDateTime.now().format(dtf))
    .start()
    .awaitTermination()
  logger.info("Hi I am from logger2")

}
