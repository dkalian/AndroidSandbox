package com.shandrakov.sandbox.functional;

public interface Converter<From, To> {
    To from(From value);
}
