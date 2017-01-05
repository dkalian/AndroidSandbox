package com.shandrakov.cleanarchitecture.screens.users.show;

import android.content.Context;

import com.shandrakov.cleanarchitecture.db.SqlUsersRepository;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specification.AllRows;
import com.shandrakov.cleanarchitecture.db.specification.RowById;
import com.shandrakov.cleanarchitecture.mvp.BasePresenter;
import com.shandrakov.cleanarchitecture.screens.users.presenter.DeleteUserPresenting;
import com.shandrakov.cleanarchitecture.screens.users.presenter.UsersListPresenting;

import rx.Observable;

public class UsersPresenter implements UsersListPresenting,
        DeleteUserPresenting,
                                       BasePresenter {
    @Override
    public Observable<SqlUser> getUsers(Context context) {
        return Observable.from(new SqlUsersRepository(context).query(new AllRows()));
    }

    @Override
    public Observable<Integer>deleteUser(int userId, Context context) {
        SqlUsersRepository usersRepository = new SqlUsersRepository(context);

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
