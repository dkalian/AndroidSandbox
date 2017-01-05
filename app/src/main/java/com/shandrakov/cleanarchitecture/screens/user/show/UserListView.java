package com.shandrakov.cleanarchitecture.screens.user.show;

import com.shandrakov.cleanarchitecture.mvp.BaseView;
import com.shandrakov.cleanarchitecture.screens.user.entity.UserName;

public interface UserListView extends BaseView {
    void removeUserInPosition(UserName userName, int position);
    void addUser(UserName userName);
}
