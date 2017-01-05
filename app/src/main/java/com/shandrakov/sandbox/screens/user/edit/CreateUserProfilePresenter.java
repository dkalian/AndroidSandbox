package com.shandrakov.sandbox.screens.user.edit;

import android.content.Context;

import com.shandrakov.sandbox.db.SqlUsersRepository;
import com.shandrakov.sandbox.db.entity.SqlUser;
import com.shandrakov.sandbox.db.specification.UserByEmail;
import com.shandrakov.sandbox.functional.ListUtil;
import com.shandrakov.sandbox.repository.Repository;
import com.shandrakov.sandbox.screens.user.converter.SqlUserToUserProfile;
import com.shandrakov.sandbox.screens.user.converter.UserProfileToSqlUser;
import com.shandrakov.sandbox.screens.user.entity.UserProfile;

import java.util.List;

import rx.Observable;

public class CreateUserProfilePresenter extends UserProfilePresenter {

    public CreateUserProfilePresenter(UserProfileView userListView) {
        _userProfileView = userListView;
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onSaveButtonClicked(UserProfile userProfile) {
        saveUserAction(userProfile, _userProfileView);
    }

    @Override
    protected Observable<UserProfile> saveUser(UserProfile user, Context context) {
        return Observable.create(subscriber -> {
            subscriber.onStart();

            Repository<SqlUser> usersRepository = new SqlUsersRepository(context);

            if (!user.isValid()) {
                subscriber.onError(new Exception("User is not valid."));
                return;
            }

            if (!usersRepository.query(new UserByEmail(user.email().value())).isEmpty()) {
                subscriber.onError(new Exception("User with email " + user.email().value() + " is already exist"));
                return;
            }

            usersRepository.add(UserProfileToSqlUser.create().from(user));

            List<SqlUser> savedUsers = usersRepository.query(new UserByEmail(user.email().value()));

            if (savedUsers.isEmpty()) {
                subscriber.onError(new Exception("Can not possible create new user"));
                return;
            }

            subscriber.onNext(SqlUserToUserProfile.create().from(ListUtil.head(savedUsers)));
            subscriber.onCompleted();
        });
    }

    private final UserProfileView _userProfileView;
}
