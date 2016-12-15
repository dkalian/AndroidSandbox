package com.shandrakov.cleanarchitecture.functionals;

/*
 * The Maybe provides a way to make a safety wrapper around partial functions,
 * that is, functions which can fail to work for a range of arguments.
 */
public class Maybe<T> {

    public static <T> Maybe<T> value(T value) {
        return new Maybe<>(value);
    }

    public static <T> Maybe<T> nothing() {
        return new Maybe<>();
    }

    public Maybe() {
        mValue = null;
    }

    public Maybe(T value) {
        mValue = value;
    }

    public final T value() {
        if (mValue == null)  throw new RuntimeException("Accessing value from maybe without value.");
        return mValue;
    }

    public final boolean isValue() {
        return mValue != null;
    }

    public final T or(T defaultValue) {
        if (isValue()) return value();
        else return defaultValue;
    }

    private final T mValue;
}
