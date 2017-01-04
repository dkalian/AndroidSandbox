package com.shandrakov.cleanarchitecture.screens.users.presenter;

import android.content.Context;

import com.shandrakov.cleanarchitecture.db.SqlRepoExample;
import com.shandrakov.cleanarchitecture.db.specifications.AllRows;
import com.shandrakov.cleanarchitecture.screens.users.converter.SqlUserToUserName;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserName;

import java.util.List;

import rx.Observable;

public class UserListPresenter implements IUserListPresenter {

    @Override
    public Observable<List<UserName>> getUsers(Context context) {
        return new SqlRepoExample(context)
                .query(new AllRows())
                .flatMap(users -> Observable.from(users))
                .map(sqlUser -> SqlUserToUserName.create().from(sqlUser))
                .toList();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }
}
