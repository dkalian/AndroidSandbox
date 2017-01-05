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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserName userName = (UserName) o;

        if (_id != userName._id) return false;
        return _name != null ? _name.equals(userName._name) : userName._name == null;
    }

    @Override
    public int hashCode() {
        int result = _id;
        result = 31 * result + (_name != null ? _name.hashCode() : 0);
        return result;
    }

    private final int _id;
    private final String _name;
}
