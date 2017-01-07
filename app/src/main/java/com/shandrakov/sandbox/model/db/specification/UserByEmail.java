package com.shandrakov.sandbox.model.db.specification;

import com.shandrakov.sandbox.model.db.DBContract;
import com.shandrakov.sandbox.model.db.SqlSpecification;


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
