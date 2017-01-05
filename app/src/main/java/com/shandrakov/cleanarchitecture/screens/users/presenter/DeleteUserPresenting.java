package com.shandrakov.cleanarchitecture.screens.users.presenter;

import android.content.Context;

import rx.Observable;

public interface DeleteUserPresenting {
    Observable<Integer> deleteUser(int userId, Context context);
}
