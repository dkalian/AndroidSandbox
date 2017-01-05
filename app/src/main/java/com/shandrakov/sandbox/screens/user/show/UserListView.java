package com.shandrakov.sandbox.screens.user.show;

import com.shandrakov.sandbox.mvp.BaseView;
import com.shandrakov.sandbox.screens.user.entity.UserName;

public interface UserListView extends BaseView {
    void removeUserInPosition(UserName userName, int position);
    void addUser(UserName userName);
}
