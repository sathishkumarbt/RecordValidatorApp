package org.statement.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statement.models.MT940;

import java.util.List;

public class ConsoleReport implements Report {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleReport.class);
    @Override
    public void generateReport(String fileName, List<MT940> failedRecords) {
        LOGGER.info("----------------------------------------------------------------------");
        LOGGER.info("# FileName : " + fileName + " # Number of Failed Records :" + failedRecords.size());
        failedRecords.forEach(failedValidation -> {
            LOGGER.info("Transaction Reference :" + failedValidation.getTransactionRef() + " Description : " + failedValidation.getDescription());
        });
        LOGGER.info("----------------------------------------------------------------------");
    }
}
