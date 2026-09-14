package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 1049. Last Stone Weight II
 *
 * <p>https://leetcode.com/problems/last-stone-weight-ii/description/
 */
public class LastStoneWeight2 {

    /**
     * N = stones.length, K = sum(stones[...])/2
     *
     * <p>time: O(N*K)
     *
     * <p>space: O(K)
     */
    public static int lastStoneWeightII(int[] stones) {
        Objects.requireNonNull(stones);

        int totalSum = sum(stones);
        int halfSum = totalSum / 2;

        boolean[] prevSolution = new boolean[halfSum + 1];
        prevSolution[0] = true;

        int maxReachableValue = 0;

        for (int curStone : stones) {
            boolean[] curSolution = new boolean[halfSum + 1];
            curSolution[0] = true;

            for (int i = 1; i < curSolution.length; ++i) {
                if (curStone > i) {
                    curSolution[i] = prevSolution[i];
                } else {
                    curSolution[i] = prevSolution[i] || prevSolution[i - curStone];
                }

                if (curSolution[i]) {
                    maxReachableValue = Math.max(maxReachableValue, i);
                }
            }

            prevSolution = curSolution;
        }

        return totalSum - (2 * maxReachableValue);
    }

    private static int sum(int[] arr) {
        int sum = 0;

        for (int val : arr) {
            sum += val;
        }

        return sum;
    }
}
