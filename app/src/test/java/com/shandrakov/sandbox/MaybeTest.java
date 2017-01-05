package com.shandrakov.sandbox;

import com.shandrakov.sandbox.functional.Maybe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MaybeTest {

    @Test
    public void testMaybe() {
        Maybe<String> nothing = Maybe.value(null);
        assertFalse(nothing.isValue());
        nothing = Maybe.nothing();
        assertFalse(nothing.isValue());
        String strVal = "str";
        assertTrue(nothing.or(strVal).equals(strVal));

        Maybe<String> value = Maybe.value(strVal);
        assertTrue(value.isValue());
        assertTrue(value.value().equals(strVal));
    }
}
