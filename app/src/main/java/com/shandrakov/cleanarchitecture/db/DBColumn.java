package com.shandrakov.cleanarchitecture.db;


import com.shandrakov.cleanarchitecture.functionals.Maybe;

public class DBColumn implements SqlSpecification {

    public DBColumn(final String name, final ColumnType type, final String options) {
        _name = name;
        _type = type;
        _options = Maybe.value(options);
    }

    public DBColumn(final String name, final ColumnType type) {
        this(name, type, null);
    }

    @Override
    public String query() {
        return COLUMN_PATTERN
                .replace("{name}", _name)
                .replace("{type}", _type.compile())
                .replace("{options}", _options.or(""));
    }

    private static final String COLUMN_PATTERN = "{name} {type} {options}";
    private final String _name;
    private final ColumnType _type;
    private final Maybe<String> _options;
}
