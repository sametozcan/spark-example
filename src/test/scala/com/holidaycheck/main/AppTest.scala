package com.holidaycheck.main

import com.holidaycheck.spark.DataIngestion
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

}


