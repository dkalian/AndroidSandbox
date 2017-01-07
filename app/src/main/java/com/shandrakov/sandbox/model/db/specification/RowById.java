package com.shandrakov.sandbox.model.db.specification;

import android.provider.BaseColumns;
import android.text.TextUtils;

import com.shandrakov.sandbox.model.db.SqlSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RowById implements SqlSpecification {

    public RowById(Integer ...ids) {
        _ids = new ArrayList(Arrays.asList(ids));
    }

    @Override
    public String query() {
        return BaseColumns._ID +  " IN( " + TextUtils.join(",", _ids) + " )";
    }

    private final List<Integer> _ids;
}
