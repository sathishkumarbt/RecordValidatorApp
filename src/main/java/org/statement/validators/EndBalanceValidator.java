package org.statement.validators;

import org.statement.exception.ValidationFailException;
import org.statement.models.MT940;

import java.util.List;
import java.util.stream.Collectors;

public class EndBalanceValidator implements IStatementRecordValidator {

    @Override
    public List<MT940> validate(List<MT940> records) throws ValidationFailException{
        if(null == records ){
            throw new ValidationFailException("Empty records");
        }
        //Based on the precision
        final float threshHold = .0001f;
        //compare the floating number based on the range.If difference is more than threshhold then
        // it is considered as not equal
        List<MT940> endBalanceValidatioFailedList = records.stream().filter(record -> {
            Float sum =  Float.sum(record.getStartBalance(), record.getMutation());
            Float diff = Math.abs(sum - record.getEndBalance() );
            return (diff > threshHold);
        }).collect(Collectors.toList());

        return endBalanceValidatioFailedList;
    }
}
