package com.shandrakov.cleanarchitecture.screens.user.show;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shandrakov.sandbox.R;
import com.shandrakov.cleanarchitecture.screens.user.entity.UserName;

import java.util.ArrayList;
import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.UserNameViewHolder> {

    public UsersListAdapter(OnUserLongClickListener onUserLongClickListener) {
        _onUserLongClickListener = onUserLongClickListener;
    }

    @Override
    public UserNameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_name_item, parent, false);

        return new UserNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserNameViewHolder holder, int position) {
        UserName selectedUser = _users.get(position);
        holder.userName.setText(selectedUser.name());
        holder.itemView.setOnLongClickListener(v -> {
            _onUserLongClickListener.onLongClick(selectedUser.id());
            return true;
        });
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

    public interface OnUserLongClickListener {
        void onLongClick(int userId);
    }

    private final OnUserLongClickListener _onUserLongClickListener;
    private final List<UserName> _users = new ArrayList<>();
}
