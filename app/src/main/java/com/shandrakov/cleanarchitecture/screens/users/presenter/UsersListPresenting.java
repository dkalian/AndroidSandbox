package com.shandrakov.cleanarchitecture.screens.users.presenter;

import android.content.Context;

import com.shandrakov.cleanarchitecture.db.entity.SqlUser;

import rx.Observable;

public interface UsersListPresenting {
    Observable<SqlUser> getUsers(Context context);
}
