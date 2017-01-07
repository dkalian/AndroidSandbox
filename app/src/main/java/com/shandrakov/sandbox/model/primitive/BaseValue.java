package com.shandrakov.sandbox.model.primitive;

import com.shandrakov.sandbox.model.functional.Validate;

import java.io.Serializable;

public abstract class BaseValue<T> implements Validate, Serializable
{
    public BaseValue(T value)
    {
        mValue = value;
    }

    public T value()
    {
        return mValue;
    }

    private final T mValue;
}
