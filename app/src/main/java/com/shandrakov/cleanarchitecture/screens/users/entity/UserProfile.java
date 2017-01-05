package com.shandrakov.cleanarchitecture.screens.users.entity;

import com.shandrakov.cleanarchitecture.functionals.Maybe;
import com.shandrakov.cleanarchitecture.functionals.Validate;
import com.shandrakov.cleanarchitecture.primitive.Email;
import com.shandrakov.cleanarchitecture.primitive.Name;

public class UserProfile implements Validate {

    public UserProfile(int id,
                       String firstName,
                       String lastName,
                       String email) {

        _id = Maybe.value(id);
        _firstName = new Name(firstName);
        _lastName = new Name(lastName);
        _email = new Email(email);
    }

    public UserProfile(String firstName,
                       String lastName,
                       String email) {

        _id = Maybe.nothing();
        _firstName = new Name(firstName);
        _lastName = new Name(lastName);
        _email = new Email(email);
    }

    @Override
    public boolean isValid() {
        return _firstName.isValid()
                && _lastName.isValid()
                && _email.isValid();
    }

    public Maybe<Integer> id() {
        return _id;
    }

    public Name firstName() {
        return _firstName;
    }

    public Name lastName() {
        return _lastName;
    }

    public Email email() {
        return _email;
    }

    private Maybe<Integer> _id;
    private final Name _firstName;
    private final Name _lastName;
    private final Email _email;
}
