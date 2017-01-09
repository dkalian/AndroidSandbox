package com.shandrakov.sandbox.presentation.user.presenter;

import com.shandrakov.sandbox.model.db.entity.SqlUser;
import com.shandrakov.sandbox.model.functional.Either;

public interface AddUserPresenting {
    Either<SqlUser, Exception> addUser(SqlUser user);
}
