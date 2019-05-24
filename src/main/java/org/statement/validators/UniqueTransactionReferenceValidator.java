package org.statement.validators;

import org.statement.exception.ValidationFailException;
import org.statement.models.MT940;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

public class UniqueTransactionReferenceValidator implements IStatementRecordValidator {

    @Override
    public List<MT940> validate(List<MT940> records) throws ValidationFailException {
        if (null == records) {
            throw new ValidationFailException("Empty records");
        }
        return records.stream().collect(groupingBy(MT940::getTransactionRef)).values().stream()
                .filter(refCount -> refCount.size() > 1).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
