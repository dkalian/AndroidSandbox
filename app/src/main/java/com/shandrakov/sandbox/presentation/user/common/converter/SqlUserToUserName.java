package com.shandrakov.sandbox.presentation.user.common.converter;

import com.shandrakov.sandbox.model.db.DBTable;
import com.shandrakov.sandbox.model.db.entity.SqlUser;
import com.shandrakov.sandbox.model.functional.Converter;
import com.shandrakov.sandbox.presentation.user.common.entity.UserName;

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
