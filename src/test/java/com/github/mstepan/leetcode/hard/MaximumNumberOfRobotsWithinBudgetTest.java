package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.MaximumNumberOfRobotsWithinBudget.WindowMax;
import static com.github.mstepan.leetcode.hard.MaximumNumberOfRobotsWithinBudget.maximumRobots;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumNumberOfRobotsWithinBudgetTest {

    @Test
    void case1() {
        assertEquals(3, maximumRobots(new int[] {3, 6, 1, 3, 4}, new int[] {2, 1, 3, 4, 5}, 25));
    }

    @Test
    void case2() {
        assertEquals(0, maximumRobots(new int[] {11, 12, 19}, new int[] {10, 8, 7}, 19));
    }

    @Test
    void biggerArraysCase() {
        assertEquals(
                4,
                maximumRobots(
                        new int[] {
                            74, 46, 19, 34, 7, 87, 7, 40, 28, 81, 53, 39, 3, 46, 21, 40, 76, 44, 88,
                            93, 44, 50, 22, 59, 46, 60, 36, 24, 50, 40, 56, 5, 39, 9, 24, 74, 7, 14,
                            95, 45, 36, 17, 22, 12, 53, 41, 2, 33, 100, 73, 20, 70, 81, 91, 28, 98,
                            47, 88, 79, 100, 78, 38, 44, 74, 48, 76, 73, 92, 28, 30, 95, 87
                        },
                        new int[] {
                            11, 33, 15, 40, 8, 28, 97, 89, 51, 42, 17, 57, 45, 5, 63, 53, 23, 43,
                            76, 64, 86, 86, 89, 53, 94, 91, 78, 12, 90, 29, 79, 48, 35, 6, 88, 79,
                            82, 76, 44, 93, 83, 55, 65, 96, 86, 24, 54, 65, 94, 4, 26, 73, 51, 85,
                            47, 99, 17, 14, 76, 2, 39, 52, 58, 5, 15, 35, 79, 16, 94, 16, 59, 50
                        },
                        447));
    }

    @Test
    void emptyArraysShouldBeOk() {
        assertEquals(0, maximumRobots(new int[] {}, new int[] {}, 19));
    }

    @Test
    void maxWindow() {
        WindowMax window = new WindowMax();

        // add values
        window.add(3);
        assertEquals(3, window.max());

        window.add(6);
        assertEquals(6, window.max());

        window.add(1);
        assertEquals(6, window.max());

        window.add(3);
        assertEquals(6, window.max());

        window.add(4);
        window.add(4);
        assertEquals(6, window.max());

        window.add(2);
        window.add(2);
        assertEquals(6, window.max());

        // start removing values
        window.remove(3);
        assertEquals(6, window.max());

        window.remove(6);
        assertEquals(4, window.max());

        window.remove(1);
        assertEquals(4, window.max());

        window.remove(3);
        assertEquals(4, window.max());

        window.remove(4);
        assertEquals(4, window.max());

        window.remove(4);
        assertEquals(2, window.max());

        // add new values
        window.add(5);
        assertEquals(5, window.max());

        window.add(4);
        assertEquals(5, window.max());

        window.add(20);
        window.add(20);
        assertEquals(20, window.max());

        window.add(12);
        assertEquals(20, window.max());
    }
}
