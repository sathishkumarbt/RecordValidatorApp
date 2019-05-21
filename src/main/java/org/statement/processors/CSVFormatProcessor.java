package org.statement.processors;

import org.statement.models.MT940;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVFormatProcessor implements IFormatProcessor {
    private static final String COMMA = ",";
    private static final Logger LOGGER =  LoggerFactory.getLogger(CSVFormatProcessor.class);

    @Override
    public Object processData(String fileName) {
        List<MT940> mt940List = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResource(fileName).openStream()));
            mt940List = bufferedReader.lines().skip(1).map(mapToMT940Records).collect(Collectors.toList());
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            LOGGER.error("Error occurred while retrieving data " + e.getMessage());
        }catch (IOException ioe){
            LOGGER.error("Error occurred while parsing CSV " + ioe.getMessage());
        }

        return mt940List;
    }

    private Function<String, MT940> mapToMT940Records = (line) -> {
        MT940 mt940 = new MT940();

        String[] record = line.split(COMMA);
        mt940.setTransactionRef(new Integer(record[0]));
        mt940.setAccountNumber(record[1]);
        mt940.setDescription(record[2]);
        mt940.setStartBalance(new Float(record[3]));
        mt940.setMutation(new Float(record[4]));
        mt940.setEndBalance(new Float(record[5]));
        return mt940;
    };
}
