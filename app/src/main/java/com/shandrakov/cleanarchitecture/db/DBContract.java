package com.shandrakov.cleanarchitecture.db;

public interface DBContract {
interface User {
        String NAME = "users";

        interface Columns {
            String FIRST_NAME = "first_name";
            String LAST_NAME = "last_name";
            String EMAIL = "email";
            String SERVER_ID = "server_id";
        }

        String CREATE_TABLE = DBTable.create(NAME)
                .addPrimaryKey()
                .addColumn(Columns.SERVER_ID, ColumnType.TEXT)
                .addColumn(Columns.FIRST_NAME, ColumnType.TEXT, DBTable.NOT_NULL)
                .addColumn(Columns.LAST_NAME, ColumnType.TEXT, DBTable.NOT_NULL)
                .addColumn(Columns.EMAIL, ColumnType.TEXT, DBTable.NOT_NULL)
                .query();
    }

}
