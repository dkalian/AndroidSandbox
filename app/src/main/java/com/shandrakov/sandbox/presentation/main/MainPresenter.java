package com.shandrakov.sandbox.presentation.main;

import android.support.v7.app.AppCompatActivity;

import com.shandrakov.sandbox.presentation.user.list.UsersListFragment;
import com.shandrakov.sandbox.presentation.user.profile.UserProfileActivity;

public class MainPresenter implements MainPresenting
{
    public MainPresenter(MainView view) {
        _mainView = view;
    }

    @Override
    public void onCreateNewUserButtonPressed() {
        UserProfileActivity.startCreatingActivity(_mainView.context());
    }

    @Override
    public void onStarted() {
        final AppCompatActivity activity = (AppCompatActivity) _mainView.context();
        activity.getFragmentManager()
                .beginTransaction()
                .replace(com.shandrakov.sandbox.R.id.content_fragment, new UsersListFragment())
                .commit();
    }

    @Override
    public void onStopped() {

    }

    private final MainView _mainView;
}
