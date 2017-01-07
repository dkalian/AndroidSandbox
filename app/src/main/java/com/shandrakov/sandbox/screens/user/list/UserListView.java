package com.shandrakov.sandbox.screens.user.list;

import com.shandrakov.sandbox.model.mvp.BaseView;
import com.shandrakov.sandbox.screens.user.entity.UserName;

public interface UserListView extends BaseView {
    void removeUserInPosition(UserName userName, int position);
    void addUser(UserName userName);
}
