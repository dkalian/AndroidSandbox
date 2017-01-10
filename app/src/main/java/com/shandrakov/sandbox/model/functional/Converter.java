package com.shandrakov.sandbox.model.functional;

public interface Converter<From, To> {
    To from(From value);
}
