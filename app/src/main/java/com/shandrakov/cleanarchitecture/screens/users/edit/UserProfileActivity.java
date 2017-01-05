package com.shandrakov.cleanarchitecture.screens.users.edit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.shandrakov.cleanarchitecture.R;
import com.shandrakov.cleanarchitecture.mvp.BaseActivity;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserProfile;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class UserProfileActivity extends BaseActivity
                              implements UserProfileView {

    public static final void startEditingActivity(Context context) {
        startActivity(context, Action.EDIT);
    }

    public static final void startCreatingActivity(Context context) {
        startActivity(context, Action.CREATE);
    }

    private static final void startActivity(Context context, Action action) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra(Action.class.getName(), action.name());
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        assert getIntent() != null;

        Action action = Action.valueOf(getIntent().getStringExtra(Action.class.getName()));
        final UserProfilePresenting presenter = action == Action.CREATE
                ? new CreateUserProfilePresenter()
                : new EditUserProfilePresenter();

        register(presenter);

        _firstNameET = getView(R.id.first_name);
        _lastNameET = getView(R.id.last_name);
        _emailET = getView(R.id.email);
        getView(R.id.save)
                .setOnClickListener(view -> {
                    presenter.saveUser(userFromUI(), this)
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    userProfile -> {
                                        finish();
                                    },
                                    error -> {
                                        showErrorMessage(error.getMessage());
                                    }
                            );
                }
        );
    }

    @Override
    public void showUser(UserProfile userProfile) {
        _firstNameET.setText(userProfile.firstName().value());
        _lastNameET.setText(userProfile.lastName().value());
        _emailET.setText(userProfile.email().value());
    }

    private void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private final UserProfile userFromUI() {
        return new UserProfile(
                _firstNameET.getText().toString(),
                _lastNameET.getText().toString(),
                _emailET.getText().toString());
    }

    private <T extends View> T getView(int id) {
        return (T)findViewById(id);
    }

    private AppCompatEditText _firstNameET;
    private AppCompatEditText _lastNameET;
    private AppCompatEditText _emailET;
}
