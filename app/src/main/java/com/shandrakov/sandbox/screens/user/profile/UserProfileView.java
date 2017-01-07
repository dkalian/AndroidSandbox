package com.shandrakov.sandbox.screens.user.profile;

import com.shandrakov.sandbox.model.mvp.BaseView;
import com.shandrakov.sandbox.screens.user.entity.UserProfile;

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
