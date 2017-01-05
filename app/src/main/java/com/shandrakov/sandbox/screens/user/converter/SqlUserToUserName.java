package com.shandrakov.sandbox.screens.user.converter;

import com.shandrakov.sandbox.db.DBTable;
import com.shandrakov.sandbox.db.entity.SqlUser;
import com.shandrakov.sandbox.functional.Converter;
import com.shandrakov.sandbox.screens.user.entity.UserName;

public class SqlUserToUserName implements Converter<SqlUser, UserName> {

    public static SqlUserToUserName create() {
        return new SqlUserToUserName();
    }

    @Override
    public UserName from(SqlUser sqlUser) {
        return new UserName(
                sqlUser.id().or(DBTable.EMPTY_ID),
                sqlUser.firstName() + " " + sqlUser.lastName());
    }
}
