package com.shandrakov.cleanarchitecture;

import com.shandrakov.cleanarchitecture.functionals.Either;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class EitherTest {

    @Test
    public void test() {
        Either<String, Integer> correct = Either.right("result");
        Either<String, Integer> errorCode = Either.left(404);

        assertTrue(correct.isRight());
        assertFalse(correct.isLeft());
        assertTrue(errorCode.isLeft());
        assertFalse(errorCode.isRight());
    }
}
