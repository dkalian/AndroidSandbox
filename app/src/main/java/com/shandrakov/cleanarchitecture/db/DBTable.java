package com.shandrakov.cleanarchitecture.db;

import android.provider.BaseColumns;
import android.text.TextUtils;

import com.shandrakov.cleanarchitecture.functionals.ListConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DBTable implements SqlSpecification {

    public DBTable(final String tableName) {
        _tableName = tableName;
    }

    public static DBTable create(final String tableName) {
        return new DBTable(tableName);
    }

    public DBTable addColumn(final String name, final ColumnType type) {
        _columns.add(new DBColumn(name, type));
        return this;
    }

    public DBTable addColumn(final String name, final ColumnType type, final String option) {
        _columns.add(new DBColumn(name, type, option));
        return this;
    }

    public DBTable addPrimaryKey() {
        _columns.add(new DBColumn(
                BaseColumns._ID,
                ColumnType.INTEGER,
                PRIMARY_KEY + " " + AUTOINCREMENT));
        return this;
    }

    public DBTable markAsUniqueGroup(String ...columns) {
        _options.add(unique(columns));
        return this;
    }

    public DBTable markAsForeignKye(final String column,
                                    final String referenceTable,
                                    final String referenceColumn) {
        _options.add(FOREIGN_KEY
                .replace("{column}", column)
                .replace("{ref_table}", referenceTable)
                .replace("{ref_column}", referenceColumn));

       return this;
    }

    @Override
    public String query() {
        return CREATE_TABLE
                .replace("{name}", _tableName)
                .replace("{columns}", separatedColumns(convertColumns(_columns)))
                .replace("{options}", options());
    }

    private String options() {
       return _options.isEmpty()
               ? ""
               : ", " + separatedColumns(_options);
    }

    private static List<String> convertColumns(final List<DBColumn> columns) {
        return new ListConverter<DBColumn, String>(value -> value.query()).from(columns);
    }

    private static String separatedColumns(final List<String> columns) {
        return TextUtils.join(", ", columns);
    }

    private static String unique(String ... columns) {
        assert columns.length > 1;
        return UNIQUE + "( " + separatedColumns(Arrays.asList(columns)) + ")";
    }

    public static final String PRIMARY_KEY = "PRIMARY KEY";
    public static final String FOREIGN_KEY = "FOREIGN KEY ({column}) REFERENCES {ref_table}({ref_column}) ON DELETE CASCADE";
    public static final String AUTOINCREMENT = "AUTOINCREMENT";
    public static final String NOT_NULL = "NOT NULL";
    public static final String UNIQUE = "UNIQUE";
    public static final int EMPTY_ID = 0;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS {name} ({columns} {options});";
    private final List<DBColumn> _columns = new ArrayList<>();
    private final List<String> _options = new ArrayList<>();
    private final String _tableName;
}
