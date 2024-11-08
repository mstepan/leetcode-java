package com.github.mstepan.leetcode;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Main done...");
    }

    /**
     * UVA - 11621: Small Factors https://vjudge.net/problem/UVA-11621
     *
     * <p>time: O(lgN) space: O(1)
     */
    private static int finNextWithSmallFactors(int val) {
        assert val >= 0;

        if (val == 0) {
            return 0;
        }

        int minValue = Math.min(nextPow2(val), nextPow3(val));

        for (int pow2Value = 2; pow2Value < val; pow2Value *= 2) {
            int left = (int) Math.ceil((double) val / pow2Value);
            minValue = Math.min(minValue, pow2Value * nextPow3(left));
        }

        return minValue;
    }

    private static int nextPow2(int val) {
        int pow2 = logCeil(val, 2);
        return (int) Math.pow(2.0, pow2);
    }

    private static int nextPow3(int val) {
        int pow3 = logCeil(val, 3);
        return (int) Math.pow(3.0, pow3);
    }

    private static int logCeil(int val, int base) {
        assert base == 2 || base == 3;
        return (int) Math.ceil(Math.log(val) / Math.log(base));
    }
}
