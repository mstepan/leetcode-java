package com.github.mstepan.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AllOneTest {

    @Test
    void case1() {
        AllOne allOne = new AllOne();

        assertEquals("", allOne.getMinKey());
        assertEquals("", allOne.getMaxKey());

        allOne.inc("hello");
        assertEquals("hello", allOne.getMinKey());
        assertEquals("hello", allOne.getMaxKey());

        allOne.inc("world");
        allOne.inc("world");
        assertEquals("hello", allOne.getMinKey());
        assertEquals("world", allOne.getMaxKey());
    }

    @Test
    void case2() {
        AllOne allOne = new AllOne();
        allOne.inc("a");

        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("b");
        assertEquals("a", allOne.getMinKey()); // a -> 1
        assertEquals("b", allOne.getMaxKey()); // b -> 4

        allOne.dec("b");
        assertEquals("a", allOne.getMinKey()); // a -> 1
        assertEquals("b", allOne.getMaxKey()); // b -> 3

        allOne.dec("b");
        assertEquals("a", allOne.getMinKey()); // a -> 1
        assertEquals("b", allOne.getMaxKey()); // b -> 2
    }
}
