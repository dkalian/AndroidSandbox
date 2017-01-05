package com.shandrakov.cleanarchitecture.screens.users.edit;

import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

public interface UserProfileView {

    enum Action {
        CREATE,
        EDIT
    }

    void showUser(UserProfile user);
}
