package org.statement.processors;

import org.statement.models.MT940;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVFormatProcessorTest {
    private static IFormatProcessor csvFormatProcessor;

    @BeforeAll
    private static void setup() {
        csvFormatProcessor = new ProcessorFactory().createProcessor("csv");
    }

    @Test
    void testSuccessfulProcessing(){
        String fileName = "records.csv";
        List<MT940> records = (List<MT940>)csvFormatProcessor.processData(fileName);
        assertEquals(records.size(), 10);

    }

    @Test
    void testInvalidCSVFileContent(){

    }

    @Test
    void testInvalidFileExtension(){

    }

    @Test
    void testFileNotFoundException(){

    }
}

