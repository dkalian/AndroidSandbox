package com.shandrakov.sandbox.db.specification;

import com.shandrakov.sandbox.db.DBContract;
import com.shandrakov.sandbox.db.SqlSpecification;


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
