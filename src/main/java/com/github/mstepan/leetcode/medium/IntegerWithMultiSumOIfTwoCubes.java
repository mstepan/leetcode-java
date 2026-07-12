package com.github.mstepan.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 3890. Integers with multiple sum of two cubes.
 *
 * <p>https://leetcode.com/problems/...
 */
public class IntegerWithMultiSumOIfTwoCubes {

    private static final int[] CUBES = new int[1000];

    static {
        for (int i = 1; i < CUBES.length; ++i) {
            CUBES[i] = i * i * i;
        }
    }

    public static int[] findGoodNumbers(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0, n: " + n);
        }

        List<Integer> results = new ArrayList<>();
        Set<Integer> candidates = new HashSet<>();

        MAIN:
        for (int i = 1; i < CUBES.length; ++i) {
            for (int j = i + 1; j < CUBES.length; ++j) {

                final int cur = CUBES[i] + CUBES[j];

                if (cur > n) {
                    continue MAIN;
                }

                boolean wasNew = candidates.add(cur);

                if (!wasNew) {
                    results.add(cur);
                }
            }
        }

        return toPrimitiveIntArray(results);
    }

    private static int[] toPrimitiveIntArray(List<Integer> results) {
        assert results != null;

        int[] arr = new int[results.size()];

        int i = 0;
        for (int val : results) {
            arr[i] = val;
            ++i;
        }

        return arr;
    }
}
