package com.shandrakov.cleanarchitecture.screens.users.entity;

public class UserName {

    public UserName(int id,
                    String name) {

        _id = id;
        _name = name;
    }

    public String name() {
        return _name;
    }

    public int id() {
        return _id;
    }

    private final int _id;
    private final String _name;
}
