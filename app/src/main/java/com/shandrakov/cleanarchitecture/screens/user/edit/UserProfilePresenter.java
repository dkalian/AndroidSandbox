package com.shandrakov.cleanarchitecture.screens.user.edit;

import android.content.Context;

import com.shandrakov.cleanarchitecture.mvp.BasePresenter;
import com.shandrakov.cleanarchitecture.screens.user.entity.UserProfile;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public abstract class UserProfilePresenter implements BasePresenter {
    public abstract void onSaveButtonClicked(UserProfile userProfile);
    protected abstract Observable<UserProfile> saveUser(UserProfile userProfile, Context context);

    protected void saveUserAction(UserProfile userProfile, UserProfileView view) {
          saveUser(userProfile, view.context())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            view.closeView();
                        },
                        error -> {
                            view.showError(error.getMessage());
                        }
                );
    }
}
