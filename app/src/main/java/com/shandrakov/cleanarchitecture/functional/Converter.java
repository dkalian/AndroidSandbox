package com.shandrakov.cleanarchitecture.functional;

public interface Converter<From, To> {
    To from(From value);
}
