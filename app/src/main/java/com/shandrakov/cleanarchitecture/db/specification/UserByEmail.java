package com.shandrakov.cleanarchitecture.db.specification;

import com.shandrakov.cleanarchitecture.db.DBContract;
import com.shandrakov.cleanarchitecture.db.SqlSpecification;


public class UserByEmail implements SqlSpecification, DBContract.User.Columns {

    public UserByEmail(String email) {
        _email = email;
    }

    @Override
    public String query() {
        return  EMAIL +  " = '" + _email +"'";
    }

    private final String _email;
}
