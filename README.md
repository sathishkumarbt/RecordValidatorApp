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
java -DrecordDir=<record_directory> -jar RecordValidatorApp-1.0-SNAPSHOT.jar

```
Failed Records are displayed in the console
for e.g
12:08:54.112 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
12:08:54.112 [main] INFO  o.s.r.ConsoleReport - # FileName : records.csv # Number of Failed Records :2
12:08:54.115 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :112806 Description : Clothes for Richard Dekker
12:08:54.121 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :112806 Description : Subscription for Jan Theu▀
12:08:54.122 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
12:08:54.227 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
12:08:54.228 [main] INFO  o.s.r.ConsoleReport - # FileName : records.xml # Number of Failed Records :2
12:08:54.230 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :158536 Description : Candy from Jan Bakker
12:08:54.237 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :175867 Description : Clothes from DaniÙl Theu▀
12:08:54.237 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
12:08:54.256 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
12:08:54.256 [main] INFO  o.s.r.ConsoleReport - # FileName : records_test_valid.xml # Number of Failed Records :2
12:08:54.257 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :158536 Description : Candy from Jan Bakker
12:08:54.264 [main] INFO  o.s.r.ConsoleReport - Transaction Reference :175867 Description : Clothes from DaniÙl Theu▀
12:08:54.266 [main] INFO  o.s.r.ConsoleReport - ----------------------------------------------------------------------
## Deployment

NA

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Sathish Kummar B T** - *Initial work*
