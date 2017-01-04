package com.shandrakov.cleanarchitecture.functionals;

public interface Converter<From, To> {
    To from(From value);
}
