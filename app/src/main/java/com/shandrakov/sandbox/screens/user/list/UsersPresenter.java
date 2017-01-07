package com.shandrakov.sandbox.screens.user.list;

import android.content.Context;

import com.shandrakov.sandbox.model.db.SqlUsersRepository;
import com.shandrakov.sandbox.model.db.specification.AllRows;
import com.shandrakov.sandbox.model.db.specification.RowById;
import com.shandrakov.sandbox.model.mvp.BasePresenter;
import com.shandrakov.sandbox.screens.user.converter.SqlUserToUserName;
import com.shandrakov.sandbox.screens.user.profile.UserProfileActivity;
import com.shandrakov.sandbox.screens.user.entity.UserName;
import com.shandrakov.sandbox.screens.user.presenter.DeleteUserPresenting;
import com.shandrakov.sandbox.screens.user.presenter.UsersListPresenting;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class UsersPresenter implements UsersListPresenting,
                                       DeleteUserPresenting,
                                       BasePresenter {

    public UsersPresenter(UserListView userListView) {
        _userListView = userListView;
    }

    private Observable<UserName> getUsers(Context context) {
        return Observable
                .from(new SqlUsersRepository(context).query(new AllRows()))
                .map(sqlUser -> SqlUserToUserName.create().from(sqlUser));
    }

    @Override
    public void onUserItemSwiped(UserName userName, int position) {

        deleteUser(userName.id(), _userListView.context())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        next -> {
                            _userListView.removeUserInPosition(userName, position);
                        }
                );
    }

    @Override
    public void onUserItemPressed(UserName userName, int position) {
        UserProfileActivity.startEditingActivity(_userListView.context(), userName.id());
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
        getUsers(_userListView.context())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(_userListView::addUser);
    }

    @Override
    public void onStopped() {

    }


    private final UserListView _userListView;
}
