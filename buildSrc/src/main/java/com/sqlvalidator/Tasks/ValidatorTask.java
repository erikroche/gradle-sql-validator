package com.sqlvalidator.Tasks;

import com.sqlvalidator.Extensions.ValidatorTaskExtension;

import com.sqlvalidator.Tasks.Util.OutputFileUtil;
import com.sqlvalidator.Tasks.Util.ParseFileUtil;
import com.sqlvalidator.Tasks.Util.SQLValidationUtil;
import net.sf.jsqlparser.util.validation.ValidationError;
import net.sf.jsqlparser.util.validation.feature.DatabaseType;
import org.gradle.api.DefaultTask;
import org.gradle.api.InvalidUserDataException;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Validates SQL files within a given directory
 * Usage: "./gradlew ValidatorTask"
 */
public class ValidatorTask extends DefaultTask {

public static File SQLFilesDirectory;
public static List<DatabaseType> SQLlanguage;
public static File[] filesList;

@TaskAction
    public void Validate() throws IOException {

        ValidatorTaskExtension extension = getProject().getExtensions().getByType(ValidatorTaskExtension.class);
        String sqlFilesLocation = extension.filesDirectory;
        System.out.println(sqlFilesLocation + "Some other string");
        SQLFilesDirectory = new File(sqlFilesLocation);
        SQLlanguage = extension.databaseTypes;
        // Mkdir on SQLFilesDirectory

        if (SQLFilesDirectory.exists()) {
            System.out.println("HELLO HELLO");
            filesList = SQLFilesDirectory.listFiles();
        } else {
          // throw new InvalidUserDataException("SQLFilesDirectory not configured. Please specify in build.gradle ");
        }

        // Refresh errors folder if it exists.
        if (SQLFilesDirectory.exists()) {
            OutputFileUtil.deleteDir(SQLFilesDirectory);
        }

        // Loop through files and validate each one
        for (File file : filesList) {
            String fileName = file.getName();
            String parsedFile = ParseFileUtil.parseFile(file);
            List<ValidationError> errors = SQLValidationUtil.validate(parsedFile, SQLlanguage); // Validate string and store result
            OutputFileUtil.fileLogic(errors, fileName); // Handle file logic and log result
        }
    }
}