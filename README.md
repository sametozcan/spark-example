1- Packaging configurations

Maven is used for packaging. Run "mvn clean install" under project directory to obtain jar

2- Run Configurations

The application can be run using spark-submit

--master argument is mandatory.

You can use any other spark configuration setting with spark-submit

Application takes three mandatory arguments:

-firstInputFilePath

-secondInputFilePath

-outputFilePath

Ex:

spark-submit --class com.holidaycheck.main.App --master local[*] spark-example-1.0.jar f1.json f2.json result.json

spark-submit --class com.holidaycheck.main.App --master spark://127.0.0.1:7077 spark-example-1.0.jar f1.json f2.json result.json


