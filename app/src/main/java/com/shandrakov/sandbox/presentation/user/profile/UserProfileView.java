package com.shandrakov.sandbox.presentation.user.profile;

import com.shandrakov.sandbox.presentation.common.BaseView;
import com.shandrakov.sandbox.presentation.user.entity.UserProfile;

public interface UserProfileView extends BaseView{

    enum Action {
        CREATE,
        EDIT
    }

    void showUser(UserProfile user);
    void showError(String errorMessage);
    void hideSaveButton();
    void showSaveButton();
    void closeView();
}
