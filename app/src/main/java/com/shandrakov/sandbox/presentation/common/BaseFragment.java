package com.shandrakov.sandbox.presentation.common;

import android.app.Fragment;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import rx.functions.Action1;

public abstract class BaseFragment extends Fragment {

    public <T extends BasePresenter> T register(T presenter) {
        _presenters.add(presenter);
        return presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        makeActionForPresenters(_presenters, presenter -> presenter.onStarted());
    }

    @Override
    public void onStop() {
        makeActionForPresenters(_presenters, presenter -> presenter.onStopped());
        super.onStop();
    }

    private static void makeActionForPresenters (
            List<BasePresenter> presenters,
            Action1<BasePresenter> action) {

        for (BasePresenter presenter : presenters) {
            action.call(presenter);
        }
    }

    private final List<BasePresenter> _presenters = new CopyOnWriteArrayList<>();
}
