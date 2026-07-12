package com.github.mstepan.leetcode.medium;

import java.util.*;

/**
 * 3890. Integers With Multiple Sum of Two Cubes
 *
 * <p>https://leetcode.com/problems/integers-with-multiple-sum-of-two-cubes/description/
 */
public class IntegerWithMultiSumOIfTwoCubes {

    private static final int[] CUBES = new int[1001];

    static {
        for (int i = 1; i < CUBES.length; ++i) {
            CUBES[i] = i * i * i;
        }
    }

    public static List<Integer> findGoodIntegers(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0, n: " + n);
        }

        // store candidates inside Map so we don't have duplicates when:
        // a^3 + b^3 == c^3 + d^3 == e^3 + f^3
        Map<Integer, Integer> candidates = new HashMap<>();

        List<Integer> goodNumbers = new ArrayList<>();

        MAIN:
        for (int i = 1; i < CUBES.length; ++i) {
            for (int j = i; j < CUBES.length; ++j) {

                final int cur = CUBES[i] + CUBES[j];

                if (cur > n) {
                    continue MAIN;
                }

                int totalCnt =
                        candidates.compute(cur, (notUsedKey, cnt) -> cnt == null ? 1 : cnt + 1);

                if (totalCnt == 2) {
                    goodNumbers.add(cur);
                }
            }
        }

        // sort number is ASC order according to requirements
        goodNumbers.sort(Integer::compare);

        return goodNumbers;
    }
}
