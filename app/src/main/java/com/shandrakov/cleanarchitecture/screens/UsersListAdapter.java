package com.shandrakov.cleanarchitecture.screens;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shandrakov.cleanarchitecture.R;
import com.shandrakov.cleanarchitecture.screens.users.entity.UserName;

import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserNameViewHolder> {

    @Override
    public UserNameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_name_item, parent, false);

        return new UserNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserNameViewHolder holder, int position) {
        holder.userName.setText(_users.get(position).name());
    }

    @Override
    public int getItemCount() {
        return _users.size();
    }

    final static class UserNameViewHolder extends RecyclerView.ViewHolder {
        public UserNameViewHolder(View itemView) {
            super(itemView);
            userName = (TextView) itemView.findViewById(R.id.user_name);
        }

        final TextView userName;
    }

    public void add(UserName userName) {
        _users.add(userName);
    }

    public void remove(UserName userName) {
        _users.remove(userName);
    }

    public List<UserName> users() {
        return new ArrayList<>(_users);
    }

    private List<UserName> _users = new ArrayList<>();
}
