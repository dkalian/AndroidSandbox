package com.shandrakov.sandbox.model.primitive;

import java.util.regex.Pattern;

public abstract class StringValue extends BaseValue<String>
{
    public StringValue(String value, Pattern pattern)
    {
        super(value);
        mPattern = pattern;
    }

    @Override
    public boolean isValid()
    {
        return mPattern.matcher(value()).matches();
    }

    private final Pattern mPattern;
}
