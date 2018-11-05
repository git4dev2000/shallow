package org.grl.shallow
import org.apache.spark.sql.{SparkSession, Dataset}
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.types.StructField
import org.spark_project.dmg.pmml.DataType
import org.apache.spark.sql.types.DataTypes
import org.apache.spark.sql.types.DateType

object DT {
  def main(args: Array[String]):Unit = {
    val spark = SparkSession.builder()
      .appName("DecisionTree")
      .master("local[*]")
      .getOrCreate()
  
  import spark.implicits._
  val path = "/home/mt/Downloads/KNMI_20181103.txt"
  
  val colNames = Seq(
       "STN", "Date","DDVEC", "FHVEC", "FG",
       "FHX", "FHXH", "FHN", "FHNH", "FXX",
       "FXXH", "TG", "TN", "TNH", "TX",  
       "TXH", "T10N", "T10NH", "SQ", "SP",
       "Q", "DR", "RH", "RHX", "RHXH",
       "PG", "PX", "PXH", "PN", "PNH",
       "VVN", "VVNH", "VVX", "VVXH", "NG",
       "UG", "UX", "UXH", "UN", "UNH", "EV24"
      )
      
   val dailyCols = Seq(
       "DDVEC", "FHVEC", "FG", "TG", "SQ",
       "SP", "Q", "PG", "NG", "UG", "EV24", "RH"
       )   
   
    val weatherData = spark.read
      .option("comment","#")
      .csv(path)
      .toDF(colNames:_*)
      .select(dailyCols.head, dailyCols.tail:_*)
      .withColumn("DDVEC", $"DDVEC".cast("double"))
      .withColumn("FHVEC", $"FHVEC".cast("double"))
      .withColumn("FG", $"FG".cast("double"))
      .withColumn("TG", $"TG".cast("double"))
      .withColumn("SQ", $"SQ".cast("double"))
      .withColumn("SP", $"SP".cast("double"))
      .withColumn("Q", $"Q".cast("double"))
      .withColumn("PG", $"PG".cast("double"))
      .withColumn("NG", $"NG".cast("double"))
      .withColumn("UG", $"UG".cast("double"))
      .withColumn("EV24", $"EV24".cast("double"))
      .withColumn("RH", $"RH".cast("double"))
      .na.drop()
      
      
     
  }
}

class DT (private val spark: SparkSession) {
  
  ???
 
}

