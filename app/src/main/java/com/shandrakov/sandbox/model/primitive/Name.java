package com.shandrakov.sandbox.model.primitive;

import java.util.regex.Pattern;

public class Name extends StringValue
{
    public static Name create(String value)
    {
        return new Name(value);
    }

    public static Name create(String value, Pattern pattern)
    {
        return new Name(value, pattern);
    }

    public Name(String value, Pattern pattern)
    {
        super(value, pattern);
    }

    public Name(String value)
    {
        super(value, Pattern.compile(NAME_PATTERN_DEFAULT));
    }

    private static final String NAME_PATTERN_DEFAULT = "[0-9A-Za-z \\- \\_]{2,30}";
}
