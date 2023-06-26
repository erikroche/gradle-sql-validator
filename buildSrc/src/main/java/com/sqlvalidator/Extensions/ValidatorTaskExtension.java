package com.sqlvalidator.Extensions;

import net.sf.jsqlparser.util.validation.feature.DatabaseType;

import java.util.ArrayList;
import java.util.List;

public class ValidatorTaskExtension {
    public String filesDirectory = "src/main/resources/db/sql-files";
    public List<DatabaseType> databaseTypes = new ArrayList<>();
}
