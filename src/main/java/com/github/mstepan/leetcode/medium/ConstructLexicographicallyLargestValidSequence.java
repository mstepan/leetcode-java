package com.github.mstepan.leetcode.medium;

import java.util.Arrays;

/**
 * 1718. Construct the Lexicographically Largest Valid Sequence
 *
 * <p>https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
 */
public class ConstructLexicographicallyLargestValidSequence {

    public static int[] constructDistancedSequence(int n) {
        checkInRange(n, 1, 20);

        final int expectedSolLength = n * 2 - 1;
        final BestSequence best = new BestSequence(new int[expectedSolLength]);

        tryPlaceBest(new int[expectedSolLength], n, best);

        return best.sol;
    }

    private static void tryPlaceBest(int[] sol, int val, BestSequence best) {
        if (val == 1) {
            int emptySlotIdx = findEmptySlot(sol);
            assert emptySlotIdx != -1;

            sol[emptySlotIdx] = 1;

            best.recordBestSolution(sol);

            sol[emptySlotIdx] = 0;

            return;
        }

        for (int i = 0; i < sol.length && i + val < sol.length; i++) {
            if (sol[i] == 0 && sol[i + val] == 0) {
                sol[i] = val;
                sol[i + val] = val;

                tryPlaceBest(sol, val - 1, best);

                sol[i] = 0;
                sol[i + val] = 0;
            }
        }
    }

    private static int findEmptySlot(int[] sol) {
        for (int i = 0; i < sol.length; i++) {
            if (sol[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    static class BestSequence {
        final int[] sol;

        public BestSequence(int[] sol) {
            this.sol = sol;
        }

        public void recordBestSolution(int[] curSolution) {
            int cmpRes = Arrays.compare(curSolution, this.sol);

            if (cmpRes > 0) {
                System.arraycopy(curSolution, 0, this.sol, 0, curSolution.length);
            }
        }
    }

    private static void checkInRange(int value, int from, int to) {
        if (value < from || value > to) {
            throw new IllegalArgumentException(
                    String.format("value=%d not in range [%d..%d]", value, from, to));
        }
    }
}
