package org.statement.validators;

import org.statement.models.MT940;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EndBalanceValidatorTest {
    private static IStatementRecordValidator endBalanceValidator;

    @BeforeAll
    static void setup() {
        endBalanceValidator = new EndBalanceValidator();
    }

    @Test
    public void testAllRecordEndBalanceIsCorrect() {
        MT940 correctRecord = new MT940(147534, "NL27SNSB0917829871",
                "Subscription from Vincent Dekker", 37.29f, -3.4f, 33.89f);
        MT940 failRecord = new MT940(187209, "NL32RABO0195610843",
                "Candy from Richard Theuß", 95.16f, +46.10f, 141.27f);
        List<MT940> records = new ArrayList<>();
        records.add(correctRecord);
        records.add(failRecord);
        List<MT940> errorRecords = endBalanceValidator.validate(records);
        assertEquals(errorRecords.size(), 1);
        MT940 failedRecord = errorRecords.get(0);
        assertEquals(failedRecord.getTransactionRef(),187209 );
        assertEquals(failedRecord.getDescription(),"Candy from Richard Theuß");
    }

    @Test
    public void testOneRecordEndBalanceIsINCorrect() {

    }

    @Test
    public void testALLRecordEndBalanceISINCorrect() {

    }

    @Test
    public void testEmptyRecord() {

    }

    @Test
    public void testNullInputToTheValidator() {

    }
}

