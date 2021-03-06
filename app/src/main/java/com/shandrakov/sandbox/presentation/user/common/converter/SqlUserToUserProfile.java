package com.shandrakov.sandbox.presentation.user.common.converter;

import com.shandrakov.sandbox.model.db.entity.SqlUser;
import com.shandrakov.sandbox.model.functional.Converter;
import com.shandrakov.sandbox.presentation.user.common.entity.UserProfile;

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
