package com.shandrakov.sandbox.screens.user.edit;

import com.shandrakov.sandbox.mvp.BaseView;
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
