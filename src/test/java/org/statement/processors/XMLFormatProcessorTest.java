package org.statement.processors;

import org.junit.jupiter.api.Assertions;
import org.statement.exception.FileParserException;
import org.statement.models.MT940;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class XMLFormatProcessorTest {
    private static IFormatProcessor xmlFormatProcessor;

    @BeforeAll
    static void setup() {
        xmlFormatProcessor = new ProcessorFactory().createProcessor("XML");
    }

    @Test
    void testSuccessfullyXMLISParsedAndNumberOfRecordsAreValidated(){
        URL url = getClass().getClassLoader().getResource("xml/records_test_valid.xml");
        List<MT940> mt940Records = null;
        try {
            mt940Records = xmlFormatProcessor.processData(Paths.get(url.toURI()).toFile().getAbsolutePath());
        } catch (FileParserException | URISyntaxException e) {
            e.printStackTrace();
        }
       assertEquals(10, mt940Records.size());
    }

    @Test
    void testInvalidXMLFileContent(){
        URL url = getClass().getClassLoader().getResource("xml/invalid_xml_file.xml");
        assertThrows(FileParserException.class, () -> xmlFormatProcessor.processData(Paths.get(url.toURI()).toFile().getAbsolutePath()));
    }

    @Test
    void testInvalidXMLFileExtension(){
        URL url = getClass().getClassLoader().getResource("xml/Invalid_extension_xml.x");
        List<MT940> mt940Records = null;
        try {
            mt940Records = xmlFormatProcessor.processData(Paths.get(url.toURI()).toFile().getAbsolutePath());
        } catch (FileParserException | URISyntaxException e) {
            e.printStackTrace();
        }
       assertEquals(10, mt940Records.size());
    }

    @Test
    void testFileNotFoundExceptionForXMLParser(){
        assertThrows(FileParserException.class, () -> xmlFormatProcessor.processData("xml/unknown.xml"));
    }


    @Test
    void testNullFileInputToXmlParser(){
        Assertions.assertThrows(IllegalArgumentException.class , () -> xmlFormatProcessor.processData(null));
    }
}
