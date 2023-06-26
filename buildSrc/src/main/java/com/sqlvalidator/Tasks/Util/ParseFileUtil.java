package com.sqlvalidator.Tasks.Util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Takes File as input and returns a cleaned String as output.
 */
public class ParseFileUtil {

    // String instantiated to hold the contents of the file
    static String contents = null;

    public static String parseFile(File file){
        try {
            contents = Files.readString(file.toPath(), StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Removing comments, new line characters and spaces to make String readable for Validator
        contents = contents.replaceAll("--.*", "").replaceAll("\n", " ").replaceAll("/\\*.*?\\*/", " ").replaceAll("\\s+", " ");
        return contents;
    }
}
