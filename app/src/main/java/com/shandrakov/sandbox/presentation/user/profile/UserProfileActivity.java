package com.shandrakov.sandbox.presentation.user.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.shandrakov.sandbox.R;
import com.shandrakov.sandbox.model.db.DBTable;
import com.shandrakov.sandbox.presentation.common.BaseActivity;
import com.shandrakov.sandbox.presentation.user.entity.UserProfile;

public class UserProfileActivity extends BaseActivity
                              implements UserProfileView {

    public static final void startEditingActivity(Context context, int userId) {
        Intent intent = createIntent(context, Action.EDIT);
        intent.putExtra(USER_ID_KEY, userId);
        context.startActivity(intent);
    }

    public static final void startCreatingActivity(Context context) {
        context.startActivity(createIntent(context, Action.CREATE));
    }

    private static final Intent createIntent(Context context, Action action) {
        Intent intent = new Intent(context, UserProfileActivity.class);
        intent.putExtra(Action.class.getName(), action.name());
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        assert getIntent() != null;

        Action action = Action.valueOf(getIntent().getStringExtra(Action.class.getName()));

        final UserProfilePresenter presenter = action == Action.CREATE
                ? new CreateUserProfilePresenter(this)
                : new EditUserProfilePresenter(this,
                getIntent().getIntExtra(USER_ID_KEY, DBTable.EMPTY_ID));

        register(presenter);

        _firstNameET = getView(R.id.first_name);
        _lastNameET = getView(R.id.last_name);
        _emailET = getView(R.id.email);
        _button = getView(R.id.save);
        _button.setOnClickListener(view -> {
            presenter.onSaveButtonClicked(userFromUI());
        });
    }

    @Override
    public void showUser(UserProfile userProfile) {
        _firstNameET.setText(userProfile.firstName().value());
        _lastNameET.setText(userProfile.lastName().value());
        _emailET.setText(userProfile.email().value());
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideSaveButton() {
        _button.setVisibility(View.GONE);
    }

    @Override
    public void showSaveButton() {
        _button.setVisibility(View.VISIBLE);
    }

    @Override
    public void closeView() {
       finish();
    }

    @Override
    public Context context() {
        return this;
    }

    private final UserProfile userFromUI() {
        return new UserProfile(
                _firstNameET.getText().toString().trim(),
                _lastNameET.getText().toString().trim(),
                _emailET.getText().toString().trim());
    }

    private <T extends View> T getView(int id) {
        return (T)findViewById(id);
    }

    private final static String USER_ID_KEY = "user_id_key";

    private AppCompatButton _button;
    private AppCompatEditText _firstNameET;
    private AppCompatEditText _lastNameET;
    private AppCompatEditText _emailET;
}
