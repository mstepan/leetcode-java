package com.github.mstepan.leetcode.medium;

import java.math.BigInteger;

/**
 * 2400: Number of Ways to Reach a Position After Exactly k Steps.
 * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/description/
 */
public class NumberOfWaysToReachPositionAfterExactlyKsteps {

    private static final long MOD = 1_000_000_007L;
    private static final BigInteger MOD_BIG = BigInteger.valueOf(MOD);

    public static int numberOfWays(int start, int stop, int stepsCnt) {
        assert start >= 1 && start <= 1000;
        assert stop >= 1 && stop <= 1000;
        assert stepsCnt >= 1 && stepsCnt <= 1000;

        final int absDiff = Math.abs(stop - start);

        if (absDiff > stepsCnt) {
            return 0;
        }

        int onesCnt = 0;
        int negOnesCnt = 0;

        int leftSlots;

        if (stop >= start) {
            onesCnt = stop - start;
            leftSlots = stepsCnt - onesCnt;
        } else {
            negOnesCnt = start - stop;
            leftSlots = stepsCnt - negOnesCnt;
        }

        if ((leftSlots % 2) == 0) {
            onesCnt += (leftSlots / 2);
            negOnesCnt += (leftSlots / 2);

            final long numerator = facByMod(stepsCnt);

            final long denominator = (facByMod(onesCnt) * facByMod(negOnesCnt)) % MOD;

            final long result = (numerator * modInverse(denominator)) % MOD;

            return (int) result;
        }

        return 0;
    }

    private static long modInverse(long value) {
        // MOD is prime value so mod inverse can be calculated more effectively
        // (x^1) == (x^(MOD-2)) % MOD without converting to BigInteger
        BigInteger valueAsBig = BigInteger.valueOf(value);
        return valueAsBig.modInverse(MOD_BIG).longValue();
    }

    private static long facByMod(int onesCnt) {
        long result = 1L;

        for (int i = 1; i <= onesCnt; i++) {
            result = (result * i) % MOD;
        }

        return result;
    }
}
