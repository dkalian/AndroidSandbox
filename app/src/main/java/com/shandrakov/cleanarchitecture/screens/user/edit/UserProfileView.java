package com.shandrakov.cleanarchitecture.screens.user.edit;

import com.shandrakov.cleanarchitecture.mvp.BaseView;
import com.shandrakov.cleanarchitecture.screens.user.entity.UserProfile;

public interface UserProfileView extends BaseView{

    enum Action {
        CREATE,
        EDIT
    }

    void showUser(UserProfile user);
    void shoeError(String errorMessage);
    void hideSaveButton();
    void showSaveButton();
}
