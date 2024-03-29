package org.statement.formatprocessors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProcessorFactoryTest {
    private static ProcessorFactory processorFactory;

    @BeforeAll
    static void setup(){
        processorFactory = new ProcessorFactory();
    }

    @Test
    void testCSVFormatProcessorObjectCreation(){
        IFormatProcessor formatProcessor = processorFactory.createProcessor("CSV");
        assertNotNull(formatProcessor);
        assertTrue( formatProcessor instanceof CSVFormatProcessor);
    }

    @Test
    void testXMLFormatProcessorObjectCreation(){
        IFormatProcessor formatProcessor = processorFactory.createProcessor("XML");
        assertNotNull(formatProcessor);
        assertTrue( formatProcessor instanceof XMLFormatProcessor);
    }

    @Test

    void testUnknownFormat(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> processorFactory.createProcessor("NA"));
    }
}
