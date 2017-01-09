package com.shandrakov.sandbox.presentation.user.common.presenter;

import com.shandrakov.sandbox.presentation.user.common.entity.UserName;

public interface UsersListPresenting {
    void onUserItemSwiped(UserName userName, int item);
    void onUserItemPressed(UserName userName, int item);
}
