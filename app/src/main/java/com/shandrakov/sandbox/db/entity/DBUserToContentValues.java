package com.shandrakov.sandbox.db.entity;


import android.content.ContentValues;
import android.provider.BaseColumns;

import com.shandrakov.sandbox.db.DBContract;
import com.shandrakov.sandbox.db.DBTable;
import com.shandrakov.sandbox.functional.Converter;


public class DBUserToContentValues implements Converter<SqlUser, ContentValues>,
                                              DBContract.User.Columns {
    @Override
    public ContentValues from(final SqlUser user) {
        final ContentValues contentValues = new ContentValues();

        contentValues.put(FIRST_NAME, user.firstName());
        contentValues.put(LAST_NAME, user.lastName());
        contentValues.put(EMAIL, user.email());
        contentValues.put(SERVER_ID, user.serverId().or(""));

        if (user.id().isValue() && user.id().value() > DBTable.EMPTY_ID) {
            contentValues.put(BaseColumns._ID, user.id().value());
        }

        return contentValues;
    }
}
