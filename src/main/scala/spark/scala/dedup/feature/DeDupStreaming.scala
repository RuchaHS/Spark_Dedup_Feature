package spark.scala.dedup.feature

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{StringType, StructType, TimestampType}

object DeDupStreaming {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[4]")
      .appName("DeDupStreaming")
      .getOrCreate()

    val struct = new StructType()
      .add("document_dt", StringType)
      .add("source_id", StringType)
      .add("currency_cd", StringType)
      .add("country_cd", StringType)
      .add("timestamp", TimestampType)


    val dataFrame = spark.readStream
      .schema(struct)
      .json("/Users/m0m0her/Documents/Projects/Spark_Scala_Local/src/main/resources/jsonFiles/*")

    dataFrame.printSchema()

    dataFrame.isStreaming

    val windows = dataFrame//.withColumn("timestamp", current_timestamp)
      .withWatermark("timestamp", "10 hour")
      .dropDuplicates("source_id")
       windows.printSchema()

    windows.writeStream
      .outputMode("Update")
      .format("console")
      .option("truncate", "false")
      .trigger(Trigger.Once())
      .start()
      .awaitTermination()
  }
}
