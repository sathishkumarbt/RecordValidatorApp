package org.statement.processors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.statement.exception.FileParserException;
import org.statement.models.MT940;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVFormatProcessorTest {
    private static IFormatProcessor csvFormatProcessor;

    @BeforeAll
    static void setup() {
        csvFormatProcessor = new ProcessorFactory().createProcessor("csv");
    }

    @Test
    void testSuccessfullyCSVFileISParsedAndNumberOfRecordsAreValidated(){
        String fileName = "csv/records.csv";
        URL url = getClass().getClassLoader().getResource(fileName);
        List<MT940> records = null;
        try {
            records = csvFormatProcessor.processData( Paths.get(url.toURI()).toFile().getAbsolutePath());
        } catch (FileParserException |URISyntaxException e) {
            e.printStackTrace();
        }
        assertEquals(10, records.size());
    }

    @Test
    void testInvalidCSVFileContent() {
        String fileName = "csv/invalid_csv_records.csv";
        URL url = getClass().getClassLoader().getResource(fileName);
        Assertions.assertThrows(RuntimeException.class, () -> csvFormatProcessor.processData(Paths.get(url.toURI()).toFile().getAbsolutePath()));
    }

    @Test
    void testInvalidFileExtension(){
        String fileName = "csv/records_csv_in_txt.txt";
        URL url = getClass().getClassLoader().getResource(fileName);
        List<MT940> records = null;
        try {
            records = csvFormatProcessor.processData( Paths.get(url.toURI()).toFile().getAbsolutePath());
        } catch (FileParserException |URISyntaxException e) {
            e.printStackTrace();
        }
        assertEquals(10, records.size());
    }

    @Test
    void testFileNotFoundExceptionDuringCSVParser(){
        String fileName = "file_donot_exist.csv";
        Assertions.assertThrows(FileParserException.class, () -> csvFormatProcessor.processData(fileName));
    }

    @Test
    void testNullFileInputForCSVParser(){
        Assertions.assertThrows(IllegalArgumentException.class , () -> csvFormatProcessor.processData(null));
    }
}

