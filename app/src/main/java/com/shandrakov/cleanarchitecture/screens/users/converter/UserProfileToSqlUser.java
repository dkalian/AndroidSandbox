package com.shandrakov.cleanarchitecture.screens.users.converter;

import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.functional.Converter;
import com.shandrakov.cleanarchitecture.functional.Maybe;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

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
