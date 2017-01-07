package com.shandrakov.sandbox.screens.user.presenter;

import com.shandrakov.sandbox.screens.user.entity.UserName;

public interface UsersListPresenting {
    void onUserItemSwiped(UserName userName, int item);
    void onUserItemPressed(UserName userName, int item);
}
