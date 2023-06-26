package com.sqlvalidator.Tasks.Util;

import com.sqlvalidator.Tasks.ValidatorTask;
import net.sf.jsqlparser.util.validation.ValidationError;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates a file within a new directory to hold any SQL validation errors which occur.
 */
public class OutputFileUtil {

    public static final Logger logger = LoggerFactory.getLogger(OutputFileUtil.class);

    public static void deleteDir(File file) { // Delete directory and all files inside
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    public static void createDir(File directory) {
        directory.mkdirs();
    }

    public static void writeToDir(File directory, String fileName, List<ValidationError> errors) throws IOException {
        File file = new File(directory, fileName + ".errors.txt");
        file.createNewFile();

        // Write errors to the file
        for (ValidationError v: errors) {
            FileWriter writer = new FileWriter(file);
            writer.write(String.valueOf(v));
            writer.close();
        }
    }

    public static void fileLogic(List<ValidationError> errors, String fileName) throws IOException {

        if (errors.isEmpty()) {
            logger.info("The file: '" + fileName + "' is valid");
        } else {
            if (!ValidatorTask.SQLFilesDirectory.exists()) {
                createDir(ValidatorTask.SQLFilesDirectory);
            }
            writeToDir(ValidatorTask.SQLFilesDirectory, fileName, errors);
            logger.info("The file: '" + fileName + "' is not valid, check errors file for more information");
        }
    }
}
