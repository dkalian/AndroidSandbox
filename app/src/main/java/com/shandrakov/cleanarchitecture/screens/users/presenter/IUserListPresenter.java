package com.shandrakov.cleanarchitecture.screens.users.presenter;

import android.content.Context;

import com.shandrakov.cleanarchitecture.mvp.BasePresenter;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserName;

import java.util.List;

import rx.Observable;

public interface IUserListPresenter extends BasePresenter {
    Observable<List<UserName>> getUsers(Context context);
}
