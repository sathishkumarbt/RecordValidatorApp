package org.statement.formatprocessors;

import org.statement.exception.FileParserException;
import org.statement.exception.InvalidContentException;
import org.statement.models.MT940;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVFormatProcessor implements IFormatProcessor {
    private static final String COMMA = ",";
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVFormatProcessor.class);

    @Override
    public List<MT940> processData(String fileAbsolutePath) throws FileParserException {
        if (null == fileAbsolutePath) {
            throw new IllegalArgumentException("File  cannot be processed ");
        }
        List<MT940> mt940List = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileAbsolutePath), StandardCharsets.UTF_8)) {
            mt940List = lines.skip(1).map(mapToMT940Records).collect(Collectors.toList());
        } catch ( FileNotFoundException e) {
            LOGGER.error("Error occurred while retrieving data " + e.getMessage());
            throw new FileParserException("File not found :"+fileAbsolutePath);
        } catch (IOException ioe) {
            LOGGER.error("Error occurred while parsing CSV " + ioe.getMessage());
            throw new FileParserException("Error occurred while reading file "+ fileAbsolutePath);
        }

        return mt940List;
    }

    private Function<String, MT940> mapToMT940Records = (line) -> {
        MT940 mt940 = new MT940();
        String[] record = line.split(COMMA);
        if(record.length != 6){
            throw new RuntimeException(new InvalidContentException("CSV File is corrupted"));
        }
        mt940.setTransactionRef(new Integer(record[0]));
        mt940.setAccountNumber(record[1]);
        mt940.setDescription(record[2]);
        mt940.setStartBalance(new Float(record[3]));
        mt940.setMutation(new Float(record[4]));
        mt940.setEndBalance(new Float(record[5]));
        return mt940;
    };
}
