package com.shandrakov.sandbox;

import com.shandrakov.sandbox.model.primitive.CountryCode;
import com.shandrakov.sandbox.model.primitive.Email;
import com.shandrakov.sandbox.model.primitive.Name;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class PrimitivesTest
{
    @Test
    public void nameTest()
    {
        Pattern pattern = Pattern.compile("[A-Za-z]{2,5}");
        assertTrue(Name.create("valid", pattern).isValid());
        assertFalse(Name.create("invalid", pattern).isValid());
    }


    @Test
    public void emailTest()
    {
        assertTrue(Email.crate("name@mail.com").isValid());
        assertFalse(Email.crate("namemail.com").isValid());
        assertFalse(Email.crate("name@mailcom").isValid());
    }

    @Test
    public void countryCode()
    {
        assertTrue(CountryCode.create("BY").isValid());
        assertFalse(CountryCode.create("WTF").isValid());
    }
}
