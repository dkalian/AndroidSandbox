package com.shandrakov.cleanarchitecture.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shandrakov.cleanarchitecture.db.entity.CursorToDBUser;
import com.shandrakov.cleanarchitecture.db.entity.DBUserToContentValues;
import com.shandrakov.cleanarchitecture.db.entity.SqlUser;
import com.shandrakov.cleanarchitecture.db.specification.RowById;
import com.shandrakov.cleanarchitecture.repository.Repository;
import com.shandrakov.cleanarchitecture.repository.Specification;

import java.util.ArrayList;
import java.util.List;

public class SqlUsersRepository implements Repository<SqlUser> {

    public SqlUsersRepository(Context context) {
        _dbOpenHelper = new DBOpenHelper(context);
    }

    @Override
    public void add(SqlUser value) {
        add(new ArrayList<SqlUser>(){{add(value);}});
    }

    @Override
    public void add(List<SqlUser> values) {
        SQLiteDatabase db = _dbOpenHelper.getWritableDatabase();

        for (SqlUser user : values) {
            db.insert(DBContract.User.NAME,
                    null,
                    new DBUserToContentValues().from(user));
        }
    }

    @Override
    public void update(SqlUser value) {

        if (!value.id().isValue()) return;

        SQLiteDatabase db = _dbOpenHelper.getWritableDatabase();

        db.update(DBContract.User.NAME
                ,new DBUserToContentValues().from(value)
                ,new RowById(value.id().value()).query(),
                null);
    }

    @Override
    public int remove(SqlUser user) {
        return user.id().isValue()
                ? remove(new RowById(user.id().value()))
                : 0;
    }

    @Override
    public int remove(Specification specification) {
        SqlSpecification sqlSpecification = (SqlSpecification) specification;

        SQLiteDatabase db = _dbOpenHelper.getWritableDatabase();

        return db.delete(DBContract.User.NAME,
                 sqlSpecification.query(), null);
    }

    @Override
    public List<SqlUser> query(Specification specification) {
        SQLiteDatabase db = _dbOpenHelper.getReadableDatabase();

        final Cursor cursor = db.query(
                DBContract.User.NAME,
                null, ((SqlSpecification) specification).query(), null, null, null, null);
        final List<SqlUser> users = new CursorToDBUser().from(cursor);
        cursor.close();

        return users;
    }

    private final DBOpenHelper _dbOpenHelper;
}
