package com.shandrakov.sandbox.functional;

import java.util.ArrayList;
import java.util.List;

public class ListConverter<From, To> implements Converter<List<From>, List<To>> {
    public ListConverter(final List<To> resultContainer, final Converter<From, To> converter) {
        mConverter = converter;
        mResultContainer = resultContainer;
    }

    public ListConverter(final Converter<From, To> converter) {
        this(new ArrayList<>(), converter);
    }

    @Override
    public List<To> from(final List<From> values) {
        for (From value : values) {
            mResultContainer.add(mConverter.from(value));
        }

        return mResultContainer;
    }

    private final List<To> mResultContainer;
    private final Converter<From, To> mConverter;
}
