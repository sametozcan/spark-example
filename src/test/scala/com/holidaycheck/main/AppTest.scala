package com.holidaycheck.main

import com.holidaycheck.spark.DataIngestion
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions._
import org.junit._
import Assert._
import javax.xml.bind.DatatypeConverter

@Test
class AppTest {

    @Test
    def testOK() = assertTrue(true)

    @Test
    def timeTest(): Unit = {

        val t1 = DatatypeConverter.parseDateTime("2016-06-15T12:30:24.023456Z").getTime
        val t2 = DatatypeConverter.parseDateTime("2016-06-15T12:30:24.023456+05:00").getTime
        val t3 = DatatypeConverter.parseDateTime("2016-06-15T12:30:24.023456").getTime

        assertTrue(t1.after(t2))
        assertTrue(t1.after(t3))
        assertTrue(t2.before(t3))
        assertTrue(DataIngestion.makeDT("2016-06-15T12:30:24.023456Z") > DataIngestion.makeDT("2016-06-15T14:30:24.023456+03:00"))

    }

    @Test
    def dataTest(): Unit = {

        val sc = new SparkContext()

        val sqlContext = new org.apache.spark.sql.SQLContext(sc)

        val data1 = sqlContext.read.json("f1.json")

        data1.registerTempTable("first")

        val data2 = sqlContext.read.json("f2.json")

        val makeDtUdf = udf(DataIngestion.makeDT(_:String))

        // adds new column to dataframe with milliseconds time
        val modifiedDf = data2.withColumn("datetime_formatted", makeDtUdf(data2("datetime")))
        modifiedDf.registerTempTable("second")

        assertEquals(data1.count(), 1000L)
        assertEquals(data2.count(), 1000000L)

    }



}


