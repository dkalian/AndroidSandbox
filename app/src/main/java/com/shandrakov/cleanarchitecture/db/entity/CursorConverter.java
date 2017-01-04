package com.shandrakov.cleanarchitecture.db.entity;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public abstract class CursorConverter<T> {

    public List<T> from(final Cursor cursor) {
        final List<T> events = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                events.add(convert(cursor));
            } while (cursor.moveToNext());
        }

        return events;
    }

    public abstract T convert(Cursor cursor);
}
