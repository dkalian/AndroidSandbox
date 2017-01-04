package com.shandrakov.cleanarchitecture.screens.users.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shandrakov.cleanarchitecture.R;
import com.shandrakov.cleanarchitecture.mvp.BaseFragment;
import com.shandrakov.cleanarchitecture.screens.UsersListAdapter;
import com.shandrakov.cleanarchitecture.screens.users.presenter.IUserListPresenter;
import com.shandrakov.cleanarchitecture.screens.users.presenter.UserListPresenter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class UsersListFragment extends BaseFragment
                            implements UserListView {

    public UsersListFragment() {
        register(_userListPresenter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _users = (ListView) view.findViewById(R.id.users_list);
        final UsersListAdapter adapter = new UsersListAdapter(getActivity());
        _users.setAdapter(adapter);

        _userListPresenter.getUsers(getActivity())
                .flatMap(list -> Observable.from(list))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            adapter.add(user.name());
                        }
                );
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public Context context() {
        return getActivity();
    }

    private ListView _users;
    private final IUserListPresenter _userListPresenter = new UserListPresenter();
}
