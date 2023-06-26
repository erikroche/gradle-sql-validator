package com.sqlvalidator.Tasks.Util;

import net.sf.jsqlparser.util.validation.Validation;
import net.sf.jsqlparser.util.validation.ValidationError;
import net.sf.jsqlparser.util.validation.feature.DatabaseType;

import java.util.List;

/**
 * Uses a Java library JSQL parser to validate the SQL String passed into it.
 * Takes a String as input and returns a list of Validation Errors;
 */
public class SQLValidationUtil {

    public static List<ValidationError> validate(String input, List<DatabaseType> databaseTypes) {

        // Validator library logic
        Validation validation = new Validation(databaseTypes, input);
        return validation.validate();
    }
}
