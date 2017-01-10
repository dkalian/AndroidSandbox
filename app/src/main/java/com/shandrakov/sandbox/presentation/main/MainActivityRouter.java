package com.shandrakov.sandbox.presentation.main;

import android.support.v7.app.AppCompatActivity;

import com.shandrakov.sandbox.presentation.user.list.UsersListFragment;
import com.shandrakov.sandbox.presentation.user.profile.UserProfileActivity;

public class MainActivityRouter implements MainActivityRouting {

    public MainActivityRouter(AppCompatActivity activity) {
        _activity = activity;
    }

    @Override
    public void showUsersListScreen() {
        _activity.getFragmentManager()
                .beginTransaction()
                .replace(com.shandrakov.sandbox.R.id.content_fragment, new UsersListFragment())
                .commit();
    }

    @Override
    public void showCreateProfileScreen() {
        UserProfileActivity.startCreatingActivity(_activity);
    }

    private final AppCompatActivity _activity;
}
