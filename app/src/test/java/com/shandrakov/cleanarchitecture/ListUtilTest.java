package com.shandrakov.cleanarchitecture;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static com.shandrakov.cleanarchitecture.functionals.ListUtil.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ListUtilTest {

    @Before
    public void setUp() {
        _empty.clear();
    }

    @Test
    public void testHead() {
        assertTrue(head(range(1,5)).equals(1));
    }

    @Test
    public void testLast() {
        assertTrue(head(range(1,5)).equals(5));
    }

    @Test
    public void testTail() {
        assertTrue(tail(_empty).isEmpty());
        assertTrue(tail(range(1,5)).equals(range(2,5)));
    }

    @Test
    public void testInit() {
        assertTrue(init(_empty).isEmpty());
        assertTrue(init(range(1,5)).equals(range(1,4)));
    }

    @Test
    public void testTake() {
        assertTrue(take(_empty, 3).isEmpty());
        assertTrue(take(range(1,5), 3).equals(range(1,3)));
    }

    @Test
    public void testDrop() {
        assertTrue(drop(_empty, 2).isEmpty());
        assertTrue(drop(range(1,5), 2).equals(range(3,5)));
    }

    private static List<Integer> range(int from, int to) {
        final List<Integer> range = new ArrayList<>();

        for (int i = from; i <=to; i++) {
            range.add(i);
        }

        return range;
    }

    private final List<Integer> _empty = new ArrayList<>();
}
