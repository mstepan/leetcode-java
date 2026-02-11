package com.github.mstepan.leetcode.medium;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountPrimesTest {

    @Test
    void normalCase() {
        assertEquals(0, CountPrimes.countPrimes(0));
        assertEquals(0, CountPrimes.countPrimes(1));
        assertEquals(0, CountPrimes.countPrimes(2));
        assertEquals(1, CountPrimes.countPrimes(3));
        assertEquals(4, CountPrimes.countPrimes(10));
        assertEquals(25, CountPrimes.countPrimes(100));
    }

    @Test
    void biggestPossibleValue() {
        assertEquals(348_513, CountPrimes.countPrimes(5_000_000));
    }

    @Test
    void negativeValueShouldFail() {
        assertThatThrownBy(() -> CountPrimes.countPrimes(-2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'n' should be in range [0...5_000_000] but found: -2");

        assertThatThrownBy(() -> CountPrimes.countPrimes(-99))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'n' should be in range [0...5_000_000] but found: -99");
    }

    @Test
    void aboveBoundaryShouldFail() {
        assertThatThrownBy(() -> CountPrimes.countPrimes(5_000_001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'n' should be in range [0...5_000_000] but found: 5000001");

        assertThatThrownBy(() -> CountPrimes.countPrimes(7_000_000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'n' should be in range [0...5_000_000] but found: 7000000");

        assertThatThrownBy(() -> CountPrimes.countPrimes(Integer.MAX_VALUE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                        "'n' should be in range [0...5_000_000] but found: 2147483647");
    }
}
