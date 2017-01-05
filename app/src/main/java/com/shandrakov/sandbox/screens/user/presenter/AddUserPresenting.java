package com.shandrakov.sandbox.screens.user.presenter;

import com.shandrakov.sandbox.db.entity.SqlUser;
import com.shandrakov.sandbox.functional.Either;

public interface AddUserPresenting {
    Either<SqlUser, Exception> addUser(SqlUser user);
}
