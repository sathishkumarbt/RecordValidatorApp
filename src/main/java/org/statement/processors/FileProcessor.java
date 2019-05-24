package org.statement.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statement.exception.FileParserException;
import org.statement.exception.ValidationFailException;
import org.statement.formatprocessors.IFormatProcessor;
import org.statement.formatprocessors.ProcessorFactory;
import org.statement.models.MT940;
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

public class FileProcessor implements Processor{
    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessor.class);
    private static final String DOT = ".";
    private static final int VALIDATOR_SIZE = 2;

    public void process(String recordDirectory) {
        try {
            ProcessorFactory processorFactory = new ProcessorFactory();
            List<IStatementRecordValidator> validators = new ArrayList<>(VALIDATOR_SIZE);
            validators.add(new EndBalanceValidator());
            validators.add(new UniqueTransactionReferenceValidator());
            Files.list(Paths.get(recordDirectory)).filter(Files::isRegularFile).forEach(recordFile -> {
                String fileName = recordFile.getFileName().toString();
                Optional<String> fileExtension = getFileExtension(fileName);
                List<MT940> failedRecords = new ArrayList<>();
                if (fileExtension.isPresent()) {
                    IFormatProcessor processor = processorFactory.createProcessor(fileExtension.get());
                    List<MT940> mt940records = null;
                    try {
                        mt940records = processor.processData(recordDirectory + File.separator + fileName);
                    } catch (FileParserException e) {
                        LOGGER.error("Exception occurred during parsing of file :" + recordFile.getFileName() + " Error:" + e.getMessage());
                    }

                    for (IStatementRecordValidator validator : validators) {
                        try {
                            failedRecords.addAll(validator.validate(mt940records));
                        } catch (ValidationFailException e) {
                            LOGGER.error("Exception occurred during validating the data :" + e.getMessage());
                        }
                    }

                    Report report = new ConsoleReport();
                    report.generateReport(fileName, failedRecords);
                } else {
                    LOGGER.info("File extension is not supported for the filename:" + recordFile.getFileName());
                }
            });
        } catch (IOException e) {
            LOGGER.error("Exception while parsing the file " + e.getMessage());
        }
    }

    private Optional<String> getFileExtension(String recordFile) {
        return Optional.ofNullable(recordFile).filter(file -> file.contains(DOT)).map(f -> f.substring(f.lastIndexOf(".") + 1));
    }
}




