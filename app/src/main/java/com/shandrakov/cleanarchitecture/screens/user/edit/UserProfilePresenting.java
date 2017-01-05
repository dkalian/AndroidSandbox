package com.shandrakov.cleanarchitecture.screens.user.edit;

import android.content.Context;

import com.shandrakov.cleanarchitecture.mvp.BasePresenter;
import com.shandrakov.cleanarchitecture.screens.user.entity.UserProfile;

import rx.Observable;

public interface UserProfilePresenting extends BasePresenter {
    Observable<UserProfile> saveUser(UserProfile userProfile, Context context);
}
