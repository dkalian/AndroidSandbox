package com.shandrakov.sandbox.presentation.user.list;

import com.shandrakov.sandbox.presentation.common.BaseView;
import com.shandrakov.sandbox.presentation.user.common.entity.UserName;

public interface UserListView extends BaseView {
    void removeUserInPosition(UserName userName, int position);
    void addUser(UserName userName);
}
