package com.holidaycheck.main

import org.junit._
import Assert._

@Test
class AppTest {

    @Test
    def testOK() = assertTrue(true)

    @Test
    def timeTest(): Unit = {

        println(javax.xml.bind.DatatypeConverter.parseDateTime("2016-06-15T12:30:24.023456Z").getTime)
        println(javax.xml.bind.DatatypeConverter.parseDateTime("2016-06-15T12:30:24.023456+05:00").getTime)
        println(javax.xml.bind.DatatypeConverter.parseDateTime("2016-06-15T12:30:24.023456").getTime)

        assertTrue(true)


    }

}


