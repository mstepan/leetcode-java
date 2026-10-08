package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BulbSwitcherTest {

    @Test
    void bulbSwitch() {
        assertEquals(0, BulbSwitcher.bulbSwitch(0));
        assertEquals(1, BulbSwitcher.bulbSwitch(1));
        assertEquals(1, BulbSwitcher.bulbSwitch(2));
        assertEquals(4, BulbSwitcher.bulbSwitch(16));
    }

    @Test
    void bulbSwitchNegativeValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> BulbSwitcher.bulbSwitch(-1));
        assertThrows(IllegalArgumentException.class, () -> BulbSwitcher.bulbSwitch(-5));
        assertThrows(IllegalArgumentException.class, () -> BulbSwitcher.bulbSwitch(-133));
    }
}
