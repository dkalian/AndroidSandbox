package com.shandrakov.cleanarchitecture.primitive;

import android.util.Patterns;

import java.util.regex.Pattern;

public class Email extends StringValue
{
    public static Email crate(String value)
    {
        return new Email(value);
    }

    public Email(String value)
    {
        super(value, Pattern.compile(EMAIL_PATTERN_DEFAULT));
    }

    private final static String EMAIL_PATTERN_DEFAULT = Patterns.EMAIL_ADDRESS.pattern();
}
