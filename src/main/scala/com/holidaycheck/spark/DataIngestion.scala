package com.holidaycheck.spark

import com.typesafe.config.ConfigFactory
import org.apache.log4j.Logger
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions._

/**
 * Created by sametozcan on 06/06/16.
 */
object DataIngestion {

  private val logger = Logger.getLogger(getClass)

  def run(path1:String, path2:String, resultPath:String): Unit = {

    val sc = new SparkContext()

    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val data1 = sqlContext.read.json(path1)

    data1.registerTempTable("first")

    val data2 = sqlContext.read.json(path2)

    val makeDtUdf = udf(makeDT(_:String))

    // adds new column to dataframe with milliseconds time
    val modifiedDf = data2.withColumn("datetime_formatted", makeDtUdf(data2("datetime")))
    modifiedDf.registerTempTable("second")

    // Join SQL finding matching buId's with max datetime
    val result = sqlContext.sql("SELECT M.logId, F.buId, M.price, M.datetime, F.destination FROM first F inner join " +
      "(select S.logId, S.buId, S.price, S.datetime from second S inner join (select buId, max(datetime_formatted) as datetime_formatted " +
      "from second group by buId) T where S.buId = T.buId and S.datetime_formatted = T.datetime_formatted) M " +
      "WHERE F.buId = M.buId")

    result.write.json(resultPath)

    sc.stop

  }

  // To have milliseconds from ISO formatted time strings
  def makeDT(time: String) = javax.xml.bind.DatatypeConverter.parseDateTime(time.substring(0,10) + "T" + time.substring(11)).getTimeInMillis

}
