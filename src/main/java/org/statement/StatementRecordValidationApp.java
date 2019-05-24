package org.statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statement.processors.FileProcessor;
import org.statement.processors.Processor;

public class StatementRecordValidationApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatementRecordValidationApp.class);
    private static final String RECORD_DIR = "recordDir";

    public static void main(String... args) {
        LOGGER.info("Welcome to the statement record validation application!!");
        String recordDirectory = System.getProperty(RECORD_DIR);
        if (null == recordDirectory) {
            System.err.println("Usage:java -jar <jarfile> -Drecord.dir");
            System.exit(0);
        }
        Processor fileProcessor = new FileProcessor();
        fileProcessor.process(recordDirectory);

    }

}
