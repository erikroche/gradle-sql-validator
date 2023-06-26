package com.sqlvalidator.Tasks.Util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ParseFileUtilTest {

    static File testSQLFiles = new File("src/test/resources/testSQLFiles");

    static File testFile1 = new File("src/test/resources/testSQLFiles/test_single_line_comments.sql");
    static File testFile2 = new File("src/test/resources/testSQLFiles/test_multi_line_comments.sql");
    static File testFile3 = new File("src/test/resources/testSQLFiles/test_new_lines.sql");
    static File testFile4 = new File("src/test/resources/testSQLFiles/test_mixed_types.sql");

    @BeforeAll
    public static void setup() throws IOException {

        testSQLFiles.mkdirs();

        FileWriter writer3 = new FileWriter(testFile3);
        writer3.write("SELECT column1, column2 -- This is a single-line comment\nFROM table1\n/*\nThis is a multi-line comment\nIt spans multiple lines\n*/\nWHERE column3 = 'value' -- This is another single-line comment\nAND column4 = 123;");
        writer3.close();

        FileWriter writer4 = new FileWriter(testFile4);
        writer4.write("SELECT column1, column2 -- This is a single-line comment\nFROM table1\n/*\nThis is a multi-line comment\nIt spans multiple lines\n*/\nWHERE column3 = 'value' -- This is another single-line comment\nAND column4 = 123;");
        writer4.close();
    }

    @Test
    public void testParseFile_withSingleLineComments() throws IOException {

        FileWriter writer = new FileWriter(testFile1);
        writer.write("SELECT column1, column2-- This is a comment\nFROM table1\nWHERE column3 = 'value';");
        writer.close();

        String expected = "SELECT column1, column2 FROM table1 WHERE column3 = 'value';";
        String actual = ParseFileUtil.parseFile(testFile1);
        assertEquals(expected, actual);
        testFile1.delete();
    }

    @Test
    public void testParseFile_withMultiLineComments() throws IOException {

        FileWriter writer = new FileWriter(testFile2);
        writer.write("/*\nThis is a multi-line comment\nSELECT column1, column2\nFROM table1\nWHERE column3 = 'value';\n*/\nSELECT column4, column5\nFROM table2;");
        writer.close();

        String expected = " SELECT column4, column5 FROM table2;";
        String actual = ParseFileUtil.parseFile(testFile2);
        assertEquals(expected, actual);
        testFile2.delete();
    }

    @Test
    public void testParseFile_withNewLines() throws IOException {
        FileWriter writer = new FileWriter(testFile3);
        writer.write("SELECT column1, column2\nFROM table1\nWHERE column3 = 'value';");
        writer.close();

        String expected = "SELECT column1, column2 FROM table1 WHERE column3 = 'value';";
        String actual = ParseFileUtil.parseFile(testFile3);
        assertEquals(expected, actual);
        testFile3.delete();
    }

    @Test
    public void testParseFile_withMixedTypes() throws IOException {
        FileWriter writer = new FileWriter(testFile4);
        writer.write("SELECT column1, column2 -- This is a single-line comment\nFROM table1\n/*\nThis is a multi-line comment\nIt spans multiple lines\n*/\nWHERE column3 = 'value' -- This is another single-line comment\nAND column4 = 123;");
        writer.close();

        String expected = "SELECT column1, column2 FROM table1 WHERE column3 = 'value' AND column4 = 123;";
        String actual = ParseFileUtil.parseFile(testFile4);
        assertEquals(expected, actual);
        testFile4.delete();
    }

    @AfterAll
    public static void cleanup() {
        testSQLFiles.delete();
    }
}
