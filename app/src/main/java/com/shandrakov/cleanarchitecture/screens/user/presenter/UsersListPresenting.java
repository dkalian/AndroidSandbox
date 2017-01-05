package com.shandrakov.cleanarchitecture.screens.user.presenter;


import com.shandrakov.cleanarchitecture.screens.user.entity.UserName;


public interface UsersListPresenting {
    void onUserItemSwiped(UserName userName, int item);
    void onUserItemPressed(UserName userName);
}
