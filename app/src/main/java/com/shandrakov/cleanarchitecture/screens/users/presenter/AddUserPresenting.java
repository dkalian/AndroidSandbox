package com.shandrakov.cleanarchitecture.screens.users.presenter;

import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.functional.Either;

public interface AddUserPresenting {
    Either<SqlUser, Exception> addUser(SqlUser user);
}
