package com.shandrakov.cleanarchitecture.screens;

import android.content.Context;
import android.widget.ArrayAdapter;


public class UsersListAdapter extends ArrayAdapter<String> {
    public UsersListAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }
}
