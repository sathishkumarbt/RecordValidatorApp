package org.statement.validators;

import org.statement.models.MT940;

import java.util.List;

public interface IStatementRecordValidator {
    List<MT940> validate(List<MT940> records);
}
