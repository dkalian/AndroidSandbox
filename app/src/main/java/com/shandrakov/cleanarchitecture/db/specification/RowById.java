package com.shandrakov.cleanarchitecture.db.specification;

import android.provider.BaseColumns;
import android.text.TextUtils;

import com.shandrakov.cleanarchitecture.db.SqlSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowById implements SqlSpecification {

    public RowById(Integer ...ids) {
        _ids = new ArrayList<Integer>(Arrays.asList(ids));
    }

    @Override
    public String query() {
        return BaseColumns._ID +  " IN( " + TextUtils.join(",", _ids) + " )";
    }

    private final List<Integer> _ids;
}
