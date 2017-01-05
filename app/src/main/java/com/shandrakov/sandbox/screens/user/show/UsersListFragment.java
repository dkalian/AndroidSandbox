package com.shandrakov.sandbox.screens.user.show;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shandrakov.sandbox.R;
import com.shandrakov.sandbox.mvp.BaseFragment;
import com.shandrakov.sandbox.screens.user.edit.UserProfileActivity;
import com.shandrakov.sandbox.screens.user.entity.UserName;

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

        final RecyclerView users = (RecyclerView) view.findViewById(R.id.users_list);
        users.setLayoutManager(new LinearLayoutManager(getActivity()));

        _usersListAdapter = new UsersListAdapter(
                userId -> UserProfileActivity.startEditingActivity(getActivity(), userId));

        users.setAdapter(_usersListAdapter);

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
                UserName userName = _usersListAdapter.users().get(position);
                _userListPresenter.onUserItemSwiped(userName, position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(users);

    }

    @Override
    public void removeUserInPosition(UserName userName, int position) {
        _usersListAdapter.remove(userName);
        _usersListAdapter.notifyItemRemoved(position);
    }

    @Override
    public void addUser(UserName userName) {
        _usersListAdapter.add(userName);
    }

    @Override
    public Context context() {
        return getActivity();
    }

    private final UsersPresenter _userListPresenter = new UsersPresenter(this);
    private UsersListAdapter _usersListAdapter;
}
