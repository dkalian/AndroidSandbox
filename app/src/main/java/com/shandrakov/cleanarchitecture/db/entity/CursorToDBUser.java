package com.shandrakov.cleanarchitecture.db.entity;


import android.database.Cursor;
import android.provider.BaseColumns;

import com.shandrakov.cleanarchitecture.db.DBContract;
import com.shandrakov.cleanarchitecture.functionals.Maybe;


public class CursorToDBUser extends CursorConverter<SqlUser>
                         implements DBContract.User.Columns {

    @Override
    public SqlUser convert(Cursor cursor) {
        final Maybe<Integer> id = Maybe.value(cursor.getInt(cursor.getColumnIndex(BaseColumns._ID)));
        final Maybe<String> serverId = Maybe.value(cursor.getString(cursor.getColumnIndex(SERVER_ID)));
        final String firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
        final String last_name = cursor.getString(cursor.getColumnIndex(LAST_NAME));
        final String email = cursor.getString(cursor.getColumnIndex(EMAIL));

        return new SqlUser(
                id,
                serverId,
                firstName,
                last_name,
                email);
    }
}
