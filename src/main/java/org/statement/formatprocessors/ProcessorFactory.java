package org.statement.formatprocessors;

import java.util.HashMap;
import java.util.function.Supplier;

public class ProcessorFactory {
    final static HashMap<String, Supplier<IFormatProcessor>> formatProcessorMap = new HashMap<>();
    static {
        formatProcessorMap.put("XML", XMLFormatProcessor::new);
        formatProcessorMap.put("CSV", CSVFormatProcessor::new);
    }

    public IFormatProcessor createProcessor(String fileFormat) {
        Supplier<IFormatProcessor> formatProcessorSupplier = formatProcessorMap.get(fileFormat.toUpperCase());
        if(formatProcessorSupplier != null){
            return formatProcessorSupplier.get();
        }
        throw new IllegalArgumentException("File formatter is not supported "+ fileFormat.toUpperCase());
    }
}
