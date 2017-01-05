package com.shandrakov.cleanarchitecture.screens.user.converter;

import com.shandrakov.cleanarchitecture.db.DBTable;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.functional.Converter;
import com.shandrakov.cleanarchitecture.screens.user.entity.UserName;

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
