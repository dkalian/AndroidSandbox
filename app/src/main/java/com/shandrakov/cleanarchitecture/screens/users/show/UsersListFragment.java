package com.shandrakov.cleanarchitecture.screens.users.show;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shandrakov.cleanarchitecture.R;
import com.shandrakov.cleanarchitecture.mvp.BaseFragment;
import com.shandrakov.cleanarchitecture.screens.users.converter.SqlUserToUserName;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserName;

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

        RecyclerView users = (RecyclerView) view.findViewById(R.id.users_list);
        users.setLayoutManager(new LinearLayoutManager(getActivity()));
        users.setAdapter(_listAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                UserName userName = _listAdapter.users().get(position);

                _userListPresenter
                        .deleteUser(userName.id(), getActivity())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                        next -> {
                            _listAdapter.remove(userName);
                            _listAdapter.notifyItemRemoved(position);
                        }
                );
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(users);

        _userListPresenter.getUsers(getActivity())
                .map(user -> SqlUserToUserName.create().from(user))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            _listAdapter.add(user);
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

    private final UsersListAdapter _listAdapter = new UsersListAdapter();
    private final UsersPresenter _userListPresenter = new UsersPresenter();
}
