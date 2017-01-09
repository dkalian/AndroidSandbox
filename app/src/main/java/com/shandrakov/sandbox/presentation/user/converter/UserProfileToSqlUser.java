package com.shandrakov.sandbox.presentation.user.converter;

import com.shandrakov.sandbox.model.db.entity.SqlUser;
import com.shandrakov.sandbox.model.functional.Converter;
import com.shandrakov.sandbox.model.functional.Maybe;
import com.shandrakov.sandbox.presentation.user.entity.UserProfile;

public class UserProfileToSqlUser implements Converter<UserProfile, SqlUser> {

    private UserProfileToSqlUser() {}

    public static final UserProfileToSqlUser create() {
        return new UserProfileToSqlUser();
    }

    @Override
    public SqlUser from(UserProfile userProfile) {

        assert userProfile.isValid();

        return new SqlUser(
                userProfile.id(),
                Maybe.nothing(),
                userProfile.firstName().value(),
                userProfile.lastName().value(),
                userProfile.email().value());
    }
}
