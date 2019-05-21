package org.statement.validators;

import org.statement.models.MT940;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueTransactionReferenceValidator implements IStatementRecordValidator {

    @Override
    public List<MT940> validate(List<MT940> records) {
        Set<Integer> referenceSet = new HashSet<>();
        return records.stream().filter(record -> {
            return (!referenceSet.add(record.getTransactionRef()));
        }).collect(Collectors.toList());
    }
}
