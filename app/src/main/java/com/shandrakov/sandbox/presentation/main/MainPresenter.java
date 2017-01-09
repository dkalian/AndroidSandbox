package com.shandrakov.sandbox.presentation.main;

public class MainPresenter implements MainPresenting
{
    public MainPresenter(MainView view, MainActivityRouter router) {
        _mainView = view;
        _router = router;
    }

    @Override
    public void onCreateNewUserButtonPressed() {
        _router.showCreateProfileScreen();
    }

    @Override
    public void onStarted() {
        _router.showUsersListScreen();
    }

    @Override
    public void onStopped() {

    }

    private final MainView _mainView;
    private final MainActivityRouter _router;
}
