package org.statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statement.exception.FileParserException;
import org.statement.exception.ValidationFailException;
import org.statement.models.MT940;
import org.statement.formatprocessors.IFormatProcessor;
import org.statement.formatprocessors.ProcessorFactory;
import org.statement.processors.FileProcessor;
import org.statement.processors.Processor;
import org.statement.report.ConsoleReport;
import org.statement.report.Report;
import org.statement.validators.EndBalanceValidator;
import org.statement.validators.IStatementRecordValidator;
import org.statement.validators.UniqueTransactionReferenceValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
