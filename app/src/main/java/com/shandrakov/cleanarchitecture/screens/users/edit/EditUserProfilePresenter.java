package com.shandrakov.cleanarchitecture.screens.users.edit;

import android.content.Context;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

import rx.Observable;

public class EditUserProfilePresenter implements UserProfilePresenting {

    @Override
    public Observable<UserProfile> saveUser(UserProfile userProfile, Context context) {
        return null;
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onStopped() {

    }
}
