package com.holidaycheck.main

import com.holidaycheck.spark.DataIngestion
import com.typesafe.config.ConfigFactory
import org.apache.log4j.Logger

/**
 * Created by sametozcan on 06/06/16.
 */
object App {

  private val logger = Logger.getLogger(getClass)

  def main(args: Array[String]) {

    val start = System.currentTimeMillis()
    logger.info("Challenge starts!")
    DataIngestion.run(args(0), args(1), args(2))
    logger.info("End of the challenge!")
    val end = System.currentTimeMillis()
    logger.info("Runtime: " + (end-start) + " ms")

  }

}
