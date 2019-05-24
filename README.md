#Customer Statement Processor

Application receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

## Getting Started

### Prerequisites

What things you need to install the software in your system

```
- Java 8
- maven
```

### Installing

A step by step series of examples that tell you how to get a development env running

Generating a jar for the application along with unit test execution

```
mvn package
```
This will generate RecordValidatorApp-1.0-SNAPSHOT jar
## Running the application

```
Java 8
java -DrecordDir=<record_directory> -jar RecordValidatorApp-1.0-SNAPSHOT.jar

For Java 9 and 10 , you need to add "--add-modules java.se.ee" while running as it is moved to a new module
java --add-modules java.se.ee  -DrecordDir="C:\cgi\assignment\assignment\record" -jar .\RecordValidatorApp-1.0-SNAPSHOT.j
ar
For Java 11 , you need to add the jaxb modules to the pom file
```
Failed Records are displayed in the console
for e.g
113:35:28.419 [main] INFO  o.s.StatementRecordValidationApp - Welcome to the statement record validation application!!
 13:35:28.498 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
 13:35:28.499 [main] INFO  o.s.r.ConsoleReport - # FileName : records.csv # Number of Failed Records :3
 13:35:28.501 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :112806 Description : Clothes from Jan Theu▀
 13:35:28.501 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :112806 Description : Clothes for Richard Dekker
 13:35:28.501 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :112806 Description : Subscription for Jan Theu▀
 13:35:28.501 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
 13:35:28.604 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
 13:35:28.604 [main] INFO  o.s.r.ConsoleReport - # FileName : records.xml # Number of Failed Records :2
 13:35:28.605 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :158536 Description : Candy from Jan Bakker
 13:35:28.606 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :175867 Description : Clothes from DaniÙl Theu▀
 13:35:28.606 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
 13:35:28.620 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
 13:35:28.620 [main] INFO  o.s.r.ConsoleReport - # FileName : records_test_valid.xml # Number of Failed Records :2
 13:35:28.620 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :158536 Description : Candy from Jan Bakker
 13:35:28.621 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :175867 Description : Clothes from DaniÙl Theu▀
 13:35:28.621 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
## Deployment

NA

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Sathish Kummar B T** - *Initial work*
