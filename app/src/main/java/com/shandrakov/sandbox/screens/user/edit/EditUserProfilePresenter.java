package com.shandrakov.sandbox.screens.user.edit;

import android.content.Context;

import com.shandrakov.sandbox.db.SqlUsersRepository;
import com.shandrakov.sandbox.db.entity.SqlUser;
import com.shandrakov.sandbox.db.specification.RowById;
import com.shandrakov.sandbox.db.specification.UserByEmail;
import com.shandrakov.sandbox.functional.ListUtil;
import com.shandrakov.sandbox.repository.Repository;
import com.shandrakov.sandbox.screens.user.converter.SqlUserToUserProfile;
import com.shandrakov.sandbox.screens.user.converter.UserProfileToSqlUser;
import com.shandrakov.sandbox.screens.user.entity.UserProfile;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class EditUserProfilePresenter extends UserProfilePresenter {

    public EditUserProfilePresenter(UserProfileView view, int userId) {
        _view = view;
        _userId = userId;
    }

    @Override
    public void onStarted() {
        _view.hideSaveButton();
        getUserById(_userId)
                .map(sqlUser -> SqlUserToUserProfile.create().from(sqlUser))
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(
                userProfile -> {
                    _view.showSaveButton();
                    _view.showUser(userProfile);
                },
                error -> {
                    _view.showError(error.getMessage());
                }
        );
    }

    @Override
    public void onSaveButtonClicked(UserProfile userProfile) {
        saveUserAction(userProfile, _view);
    }

    @Override
    public void onStopped() {
    }

    @Override
    protected Observable<UserProfile> saveUser(UserProfile userFromUI, Context context) {
        return Observable.create(subscriber -> {
            subscriber.onStart();

            UserProfile user = new UserProfile(
                    _userId,
                    userFromUI.firstName().value(),
                    userFromUI.lastName().value(),
                    userFromUI.email().value());

            if (!user.isValid()) {
                subscriber.onError(new Exception("User is not valid."));
                return;
            }

            Repository<SqlUser> usersRepository = new SqlUsersRepository(context);

            usersRepository.update(UserProfileToSqlUser.create().from(user));

            List<SqlUser> savedUsers = usersRepository.query(new UserByEmail(user.email().value()));
            subscriber.onNext(SqlUserToUserProfile.create().from(ListUtil.head(savedUsers)));
            subscriber.onCompleted();
        });
    }


    private Observable<SqlUser> getUserById(int id) {
        return Observable.create(subscriber -> {
            subscriber.onStart();
            Repository<SqlUser> userRepository = new SqlUsersRepository(_view.context());
            SqlUser user = ListUtil.head(userRepository.query(new RowById(id)));
            subscriber.onNext(user);
            subscriber.onCompleted();
        });
    }

    private final UserProfileView _view;
    private final int _userId;
}
