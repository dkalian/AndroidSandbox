package com.shandrakov.sandbox.model.primitive;

import java.util.Arrays;
import java.util.Locale;

public class CountryCode extends BaseValue<String>
{
    public static CountryCode create(String mISOCountryCode)
    {
        return new CountryCode(mISOCountryCode);
    }

    public CountryCode(String countryCode)
    {
        super(countryCode);
    }

    @Override
    public boolean isValid()
    {
        return Arrays.asList(Locale.getISOCountries()).contains(value());
    }

    public String name()
    {
        return new Locale("", value()).getDisplayCountry();
    }

    public String nameForLocale(Locale outLocale)
    {
        return new Locale("", value()).getDisplayCountry(outLocale);
    }
}
