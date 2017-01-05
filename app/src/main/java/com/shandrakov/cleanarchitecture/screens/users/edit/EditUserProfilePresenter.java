package com.shandrakov.cleanarchitecture.screens.users.edit;

import android.content.Context;

import com.shandrakov.cleanarchitecture.db.SqlUsersRepository;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specifications.RowById;
import com.shandrakov.cleanarchitecture.db.specifications.UserByEmail;
import com.shandrakov.cleanarchitecture.functional.ListUtil;
import com.shandrakov.cleanarchitecture.repository.Repository;
import com.shandrakov.cleanarchitecture.screens.users.converter.SqlUserToUserName;
import com.shandrakov.cleanarchitecture.screens.users.converter.SqlUserToUserProfile;
import com.shandrakov.cleanarchitecture.screens.users.converter.UserProfileToSqlUser;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class EditUserProfilePresenter implements UserProfilePresenting {

    public EditUserProfilePresenter(UserProfileView view, int userId) {
        _view = view;
        _userId = userId;
    }

    @Override
    public Observable<UserProfile> saveUser(UserProfile userFromUI, Context context) {
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

                }
        );
    }

    @Override
    public void onStopped() {

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
