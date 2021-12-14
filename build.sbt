name := "spark_dedup_feature"

version := "0.1"

scalaVersion := "2.12.12"

val sparkVersion = "2.4.8"

libraryDependencies ++= Seq (
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "com.google.cloud.bigdataoss" % "gcs-connector" % "hadoop2-2.1.6",
  "javax.jms" % "javax.jms-api" % "2.0"
)
