package com.shandrakov.cleanarchitecture.screens.users.converter;

import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.functionals.Converter;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

public class SqlUserToUserProfile implements Converter<SqlUser, UserProfile> {
    private SqlUserToUserProfile() {}

    public static final SqlUserToUserProfile create() {
        return new SqlUserToUserProfile();
    }

    @Override
    public UserProfile from(SqlUser value) {
        return new UserProfile(
                value.id().value(),
                value.firstName(),
                value.lastName(),
                value.email());
    }
}
