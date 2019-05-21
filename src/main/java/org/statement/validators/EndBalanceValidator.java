package org.statement.validators;

import org.statement.models.MT940;

import java.util.List;
import java.util.stream.Collectors;

public class EndBalanceValidator implements IStatementRecordValidator {

    @Override
    public List<MT940> validate(List<MT940> records) {
        List<MT940> endBalanceValidatioFailedList = records.stream().filter(record -> {
            return Float.compare(record.getEndBalance(), Float.sum(record.getStartBalance(), record.getMutation())) != 0;
        }).collect(Collectors.toList());

        return endBalanceValidatioFailedList;
    }
}
