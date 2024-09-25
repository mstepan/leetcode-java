package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.HappyStudents.countWays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HappyStudentsTest {

    @Test
    void countWaysCase1() {
        assertEquals(2, countWays(arrayList(1, 1)));
    }

    @Test
    void countWaysCase2() {
        assertEquals(3, countWays(arrayList(6, 0, 3, 3, 6, 7, 2, 7)));
    }

    private static List<Integer> arrayList(int... arr) {
        assertNotNull(arr);
        List<Integer> data = new ArrayList<>(arr.length);

        for (int val : arr) {
            data.add(val);
        }

        return data;
    }
}
