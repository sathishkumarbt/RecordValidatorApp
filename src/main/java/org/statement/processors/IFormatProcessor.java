package org.statement.processors;

import org.statement.exception.FileParserException;
import org.statement.models.MT940;

import java.util.List;

public interface IFormatProcessor {
    List<MT940> processData(String fileAbsolutePath) throws FileParserException;
}
