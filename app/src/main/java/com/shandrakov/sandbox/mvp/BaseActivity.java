package com.shandrakov.sandbox.mvp;

import android.app.Activity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import rx.functions.Action1;

public class BaseActivity extends Activity {

    public void register(BasePresenter presenter) {
        _presenters.add(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        makeActionForPresenters(_presenters, presenter -> presenter.onStarted());
    }

    @Override
    protected void onStop() {
        makeActionForPresenters(_presenters, presenter -> presenter.onStopped());
        super.onStop();
    }

    private static void makeActionForPresenters(
            List<BasePresenter> presenters,
            Action1<BasePresenter> action) {

        for (BasePresenter presenter : presenters) {
            action.call(presenter);
        }
    }

    private final List<BasePresenter> _presenters = new CopyOnWriteArrayList<>();
}
