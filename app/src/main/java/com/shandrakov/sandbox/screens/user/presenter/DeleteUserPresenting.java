package com.shandrakov.sandbox.screens.user.presenter;

import android.content.Context;

import rx.Observable;

public interface DeleteUserPresenting {
    Observable<Integer> deleteUser(int userId, Context context);
}
