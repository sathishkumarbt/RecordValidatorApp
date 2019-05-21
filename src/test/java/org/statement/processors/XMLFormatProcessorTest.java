package org.statement.processors;

import org.statement.models.xml.XMLRecords;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XMLFormatProcessorTest {
    private static IFormatProcessor xmlFormatProcessor;

    @BeforeAll
    static void setup() {
        xmlFormatProcessor = new ProcessorFactory().createProcessor("XML");
    }

    @Test
    void testSuccessfulProcessing(){
       XMLRecords xmlRecords = (XMLRecords) xmlFormatProcessor.processData("records_test_valid.xml");
       assertEquals(xmlRecords.getRecord().size(), 10);
    }

    @Test
    void testInvalidXMLFileContent(){

    }

    @Test
    void testInvalidFileExtension(){

    }

    @Test
    void testFileNotFoundException(){

    }
}
