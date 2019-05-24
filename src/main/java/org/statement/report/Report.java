package org.statement.report;

import org.statement.models.MT940;

import java.util.List;

public interface Report {
    void generateReport(String fileName, List<MT940> failedRecords);
}
