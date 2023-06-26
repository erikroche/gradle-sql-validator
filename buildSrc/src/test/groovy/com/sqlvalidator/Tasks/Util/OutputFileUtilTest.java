package com.sqlvalidator.Tasks.Util;

import net.sf.jsqlparser.util.validation.ValidationError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputFileUtilTest {

    @Test
    public void testDeleteDir() {
        File tempDir = new File("src/test/resources/test-dir");
        tempDir.mkdirs();
        OutputFileUtil.deleteDir(tempDir);
        assertFalse(tempDir.exists());
    }

    @Test
    public void testCreateDir() {
        File directoryMock = new File("src/test/resources/mockDir");
        OutputFileUtil.createDir(directoryMock);
        assertTrue(directoryMock.exists());
        OutputFileUtil.deleteDir(directoryMock);
    }


    @Test
    public void testWriteToDir() throws IOException {
        List<ValidationError> errors = new ArrayList<>();
        errors.add(new ValidationError("Sample SQL error"));
        File directoryMock = new File("src/test/resources/mockDir");
        directoryMock.mkdirs();
        OutputFileUtil.writeToDir(directoryMock, "test", errors);
        assertEquals(1, errors.size(), "There should be one error in errors");

        // Clean up
        File testErrors = new File("src/test/resources/mockDir/test.errors.txt");
        testErrors.delete();
        directoryMock.delete();
    }
}