package org.statement.processors;

import jdk.internal.org.xml.sax.SAXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.statement.exception.FileParserException;
import org.statement.models.MT940;
import org.statement.models.xml.XMLRecords;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

public class XMLFormatProcessor implements IFormatProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLFormatProcessor.class);
    private static final String SCHEMA_FILE = "xml/records.xsd" ;

    @Override
    public List<MT940> processData(String fileAbsolutePath) throws FileParserException {
        if (null == fileAbsolutePath) {
            throw new IllegalArgumentException("File  cannot be processed ");
        }
        XMLRecords xmlRecords  = new XMLRecords();
        try {
            validateXML(fileAbsolutePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLRecords.class);
            Unmarshaller jaxbContextUnMarshaller = jaxbContext.createUnmarshaller();
            xmlRecords = (XMLRecords)jaxbContextUnMarshaller.unmarshal(new File(fileAbsolutePath));

        } catch (JAXBException e) {
            LOGGER.error("Exception Occurred while unmarshaling " +e.getMessage());
            throw  new FileParserException("Error occurred while extracting data from :"+fileAbsolutePath);
        }
        return xmlRecords.getRecord();
    }

    private void validateXML(String fileAbsolutePath) throws FileParserException {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(getResource(SCHEMA_FILE)));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(fileAbsolutePath)));

        } catch (IOException e) {
            LOGGER.error("Invalid XML File "+e.getMessage());
            throw new FileParserException("Invalid XML file");

        } catch (org.xml.sax.SAXException e) {
            LOGGER.error("Invalid XML File "+e.getMessage());
            throw new FileParserException("Invalid XML file");
        }
    }

    private String getResource(String filename) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }
}
