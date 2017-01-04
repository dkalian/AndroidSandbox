package com.shandrakov.cleanarchitecture.screens.users.presenter;

import android.content.Context;

import com.shandrakov.cleanarchitecture.db.SqlRepoExample;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specifications.AllRows;
import com.shandrakov.cleanarchitecture.db.specifications.RowById;
import com.shandrakov.cleanarchitecture.mvp.BasePresenter;

import rx.Observable;

public class UsersPresenter implements UsersListPresenting,
                                       DeleteUserPresenting,
                                       BasePresenter {

    @Override
    public Observable<SqlUser> getUsers(Context context) {
        return Observable.from(new SqlRepoExample(context).query(new AllRows()));
    }

    @Override
    public Observable<Integer>deleteUser(int userId, Context context) {
        SqlRepoExample usersRepository = new SqlRepoExample(context);

        final int count = usersRepository.remove(new RowById(userId));

        return Observable.create(
                subscriber -> {
                    subscriber.onStart();
                    subscriber.onNext(count);
                    subscriber.onCompleted();
                }
        );
    }

    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }

}
