package org.statement.processors;

import org.statement.models.xml.XMLRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLFormatProcessor implements IFormatProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(XMLFormatProcessor.class);

    @Override
    public Object processData(String fileName) {
        XMLRecords xmlRecords  = new XMLRecords();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLRecords.class);
            Unmarshaller jaxbContextUnMarshaller = jaxbContext.createUnmarshaller();

            xmlRecords = (XMLRecords)jaxbContextUnMarshaller.unmarshal(getClass().getClassLoader().getResource(fileName));


        } catch (JAXBException e) {
            LOGGER.error("Exception Occurred while unmarshalling " +e.getMessage());
        }
        return xmlRecords;
    }
}
