package org.statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statement.exception.FileParserException;
import org.statement.exception.ValidationFailException;
import org.statement.models.MT940;
import org.statement.processors.IFormatProcessor;
import org.statement.processors.ProcessorFactory;
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
    public static final String RECORD_DIR = "recordDir";
    private static final int MAX_THREAD = 6;
    public static final String DOT = ".";
    private static final int VALIDATOR_SIZE = 2;

    public static void main(String... args)  {
        LOGGER.info("Welcome to the statement record validation application!!");
        String recordDirectory = System.getProperty(RECORD_DIR);
        if(null == recordDirectory){
            System.err.println("Usage:java -jar <jarfile> -Drecord.dir");
            System.exit(0);
        }
        try {
            ProcessorFactory processorFactory = new ProcessorFactory();
            List<IStatementRecordValidator> validators = new ArrayList<>(VALIDATOR_SIZE);
            validators.add(new EndBalanceValidator());
            validators.add(new UniqueTransactionReferenceValidator());
            StringBuilder reportBuffer = new StringBuilder();
            Files.list(Paths.get(recordDirectory)).filter(Files::isRegularFile).forEach(recordFile -> {
                Optional<String> fileExtension = getFileExtension(recordFile.getFileName().toString());
                List<MT940> failedRecords = new ArrayList<>();
                if(fileExtension.isPresent()){
                    IFormatProcessor processor = processorFactory.createProcessor(fileExtension.get());
                    List<MT940> mt940records = null;
                    try {
                        mt940records = processor.processData(recordDirectory+ File.separator+recordFile.getFileName().toString());
                    } catch (FileParserException e) {
                        LOGGER.error("Exception occurred during parsing of file :"+recordFile.getFileName() + " Error:"+e.getMessage());
                    }

                    for (IStatementRecordValidator validator:validators) {
                        try {
                            failedRecords.addAll(validator.validate(mt940records));
                        } catch (ValidationFailException e) {
                            LOGGER.error("Exception occurred during validating the data :"+e.getMessage());
                        }
                    }

                    System.out.println("# FileName : " +recordFile.getFileName().toString() +" # Number of Failed Records :" + failedRecords.size());
                    failedRecords.forEach(failedValidation -> { System.out.println("Transaction Reference :"+ failedValidation.getTransactionRef() + " Description : "+ failedValidation.getDescription());});
                    System.out.println("----------------------------------------------------------------------");
                }else{
                    LOGGER.info("File extension is not supported for the filename:"+recordFile.getFileName());
                }


            });
        } catch (IOException e) {
            LOGGER.error("Exception while parsing the file " + e.getMessage());
        }
    }

    private static Optional<String> getFileExtension(String recordFile) {
        return Optional.ofNullable(recordFile).filter(file -> file.contains(DOT)).map(f-> f.substring(f.lastIndexOf(".")+1));
    }
}
