package com.github.mstepan.leetcode.medium;

import java.util.BitSet;

/**
 * 204. Count Primes
 *
 * <p>https://leetcode.com/problems/count-primes/description
 */
public class CountPrimes {

    /**
     * Use Sieve of Eratosthenes to find all primes below 'N'
     *
     * <p>time: O(N*sqrt(N)) or more precisely O(N*lglgN)
     *
     * <p>space: O(N)
     */
    public static int countPrimes(int upperBoundary) {
        if (upperBoundary < 0 || upperBoundary > 5_000_000) {
            throw new IllegalArgumentException(
                    String.format(
                            "'n' should be in range [0...5_000_000] but found: %d", upperBoundary));
        }
        if (upperBoundary < 3) {
            return 0;
        }

        BitSet composite = new BitSet(upperBoundary);
        composite.set(0);
        composite.set(1);

        final int upperValue = (int) (Math.sqrt(upperBoundary)) + 1;

        for (int idx = composite.nextClearBit(2);
                idx <= upperValue;
                idx = composite.nextClearBit(idx + 1)) {
            assert idx < composite.size();
            assert !composite.get(idx);

            for (int compositeIdx = idx * idx; compositeIdx < upperBoundary; compositeIdx += idx) {
                assert compositeIdx < composite.size();
                composite.set(compositeIdx);
            }
        }

        return upperBoundary - composite.cardinality();
    }
}
