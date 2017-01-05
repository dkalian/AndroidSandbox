package com.shandrakov.cleanarchitecture.primitive;

import com.shandrakov.cleanarchitecture.functionals.Validate;

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
