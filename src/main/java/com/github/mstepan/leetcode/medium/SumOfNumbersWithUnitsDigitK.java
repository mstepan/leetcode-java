package com.github.mstepan.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2310. Sum of Numbers With Units Digit K
 *
 * <p>https://leetcode.com/problems/sum-of-numbers-with-units-digit-k/description/
 */
public class SumOfNumbersWithUnitsDigitK {

    /**
     * N = num
     *
     * <p>time: O(N^2)
     *
     * <p>space: O(N^2)
     */
    public static int minimumNumbers(int num, int k) {
        assert num >= 0;
        assert k >= 0 && k <= 9;

        return optimal(num, k, new HashMap<>());
    }

    private static int optimal(int value, int k, Map<Integer, Integer> cache) {
        if (value == 0) {
            return 0;
        }

        Integer resFromCache = cache.get(value);

        if (resFromCache != null) {
            return resFromCache;
        }

        int candidate = (k == 0) ? 10 : k;
        int bestSoFar = -1;

        while (candidate <= value) {

            int curBest = optimal(value - candidate, k, cache);

            if (curBest != -1) {
                if (bestSoFar == -1 || curBest + 1 < bestSoFar) {
                    bestSoFar = curBest + 1;
                }
            }

            candidate += 10;
        }

        cache.put(value, bestSoFar);

        return bestSoFar;
    }
}
