package com.shandrakov.cleanarchitecture.screens.users.edit;

import android.content.Context;

import com.shandrakov.cleanarchitecture.db.SqlUsersRepository;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specifications.UserByEmail;
import com.shandrakov.cleanarchitecture.functional.ListUtil;
import com.shandrakov.cleanarchitecture.repository.Repository;
import com.shandrakov.cleanarchitecture.screens.users.converter.SqlUserToUserProfile;
import com.shandrakov.cleanarchitecture.screens.users.converter.UserProfileToSqlUser;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

import java.util.List;

import rx.Observable;

public class CreateUserProfilePresenter implements UserProfilePresenting {

    @Override
    public Observable<UserProfile> saveUser(UserProfile user, Context context) {
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

    @Override
    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }
}
