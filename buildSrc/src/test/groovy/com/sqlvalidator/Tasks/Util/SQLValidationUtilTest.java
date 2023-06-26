package com.sqlvalidator.Tasks.Util;

import static org.junit.jupiter.api.Assertions.*;

import net.sf.jsqlparser.util.validation.feature.DatabaseType;
import org.junit.jupiter.api.Test;
import net.sf.jsqlparser.util.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class SQLValidationUtilTest {

    @Test
    public void testValidateWithValidSQL() {
        String validSQL = "SELECT * FROM table WHERE column = 'value';";
        List<DatabaseType> dbType = new ArrayList<>();
        dbType.add(DatabaseType.MYSQL);
        List<ValidationError> errors = SQLValidationUtil.validate(validSQL, dbType);
        assertTrue(errors.isEmpty(), "No validation errors should be found for valid SQL");
    }

    @Test
    public void testValidateWithInvalidSQL() {
        String invalidSQL = "SELECT * FRO table WHERE column = value;"; // FROM spelling incorrect
        List<DatabaseType> dbType = new ArrayList<>();
        dbType.add(DatabaseType.MYSQL);
        List<ValidationError> errors = SQLValidationUtil.validate(invalidSQL, dbType);
        assertFalse(errors.isEmpty(), "Validation errors should be found for invalid SQL");
        assertEquals(1, errors.size(), "There should be one validation error");
    }

    @Test
    public void testValidateWithNullInput() {
        List<DatabaseType> dbType = new ArrayList<>();
        dbType.add(DatabaseType.MYSQL);
        assertThrows(NullPointerException.class, () -> SQLValidationUtil.validate(null, dbType),
                "NullPointerException should be thrown for null input");
    }
}
