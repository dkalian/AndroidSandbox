package com.shandrakov.cleanarchitecture.db.entity;

import com.shandrakov.cleanarchitecture.functional.Maybe;

public class SqlUser {

    public SqlUser(final Maybe<Integer> id,
                   final Maybe<String> serverId,
                   final String firstName,
                   final String lastName,
                   final String email) {
        _id = id;
        _serverId = serverId;
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
    }

     public SqlUser(final String firstName,
                    final String lastName,
                    final String email) {
        _id = Maybe.nothing();
        _serverId = Maybe.nothing();
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
    }


    public Maybe<Integer> id() {
        return _id;
    }

    public Maybe<String> serverId() {
        return _serverId;
    }

    public String firstName() {
        return _firstName;
    }

    public String lastName() {
        return _lastName;
    }

    public String email() {
        return _email;
    }

    private final Maybe<Integer> _id;
    private final Maybe<String> _serverId;
    private final String _firstName;
    private final String _lastName;
    private final String _email;
}
