package com.github.mstepan.leetcode.medium;

/**
 * 319. Bulb Switcher
 *
 * <p>https://leetcode.com/problems/bulb-switcher/description/
 */
public class BulbSwitcher {

    /**
     * time: O(sqrt(N))
     *
     * <p>space: O(1)
     */
    public static int bulbSwitch(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("Can't handle negative value");
        }

        if (n < 2) {
            return n;
        }

        int leftSize = n;
        int bitsSetCnt = 0;

        for (int zerosCnt = 2; leftSize > 0; zerosCnt += 2) {
            leftSize = leftSize - 1 - zerosCnt;
            ++bitsSetCnt;
        }

        return bitsSetCnt;
    }

    //    public static void main(String[] args) {
    //        for (int i = 0; i <= 100; ++i) {
    //            System.out.printf("{%d}: %s%n", i, bulbSwitchString(i));
    //        }
    //    }
    //
    //    private static String bulbSwitchString(int n) {
    //        if (n == 0) {
    //            return "";
    //        }
    //
    //        if (n == 1) {
    //            return "1";
    //        }
    //
    //        final boolean[] result = new boolean[n];
    //        Arrays.fill(result, true);
    //
    //        for (int offset = 2; offset <= n; ++offset) {
    //            for (int idx = offset - 1; idx < result.length; idx += offset) {
    //                result[idx] = !result[idx];
    //            }
    //        }
    //
    //        StringBuilder resultAsStr = new StringBuilder(n);
    //
    //        for (boolean bit : result) {
    //            resultAsStr.append(bit ? "1" : "0");
    //        }
    //
    //        return resultAsStr.toString();
    //    }
}
