package org.statement.validators;

import org.statement.exception.ValidationFailException;
import org.statement.models.MT940;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UniqueTransactionReferenceValidatorTest  {
    private static IStatementRecordValidator uniqueTransactionRefValidator;

    @BeforeAll
    static void setup() {
        uniqueTransactionRefValidator = new UniqueTransactionReferenceValidator();
    }

    @Test
    public void testListOfUniqueReference(){
        MT940 firstUniqueRecord = new MT940(147534, "NL27SNSB0917829871",
                "Subscription from Vincent Dekker", 37.29f, -3.4f, 33.89f);
        MT940 secondUniqueRecord = new MT940(187209, "NL32RABO0195610843",
                "Candy from Richard Theuß", 95.16f, +46.10f, 141.27f);
        List<MT940> records = new ArrayList<>();
        records.add(firstUniqueRecord);
        records.add(secondUniqueRecord);
        List<MT940> errorRecords = null;
        try {
            errorRecords = uniqueTransactionRefValidator.validate(records);
        } catch (ValidationFailException e) {
            e.printStackTrace();
        }
        assertEquals(0 , errorRecords.size());
    }

    @Test
    public void testListOfDuplicateReference(){
        MT940 uniqueRecord = new MT940(147534, "NL27SNSB0917829871",
                "Subscription from Vincent Dekker", 37.29f, -3.4f, 33.89f);
        MT940 firstDuplicateRecord = new MT940(187209, "NL32RABO0195610843",
                "Candy from Richard Theuß", 95.16f, +46.10f, 141.27f);
        MT940 secondDuplicateRecord = new MT940(187209, "NL32RABO0195610843",
                "Candy from Working Theuß", 95.16f, +46.10f, 141.27f);
        List<MT940> records = new ArrayList<>();
        records.add(uniqueRecord);
        records.add(firstDuplicateRecord);
        records.add(secondDuplicateRecord);
        List<MT940> errorRecords = null;
        try {
            errorRecords = uniqueTransactionRefValidator.validate(records);
        } catch (ValidationFailException e) {
            e.printStackTrace();
        }
        assertEquals(2, errorRecords.size());
    }

    @Test
    public void testNullInputToTheUniqueRefValidator() {
        assertThrows(ValidationFailException.class, ()-> uniqueTransactionRefValidator.validate(null));
    }



}
