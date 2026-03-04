package com.github.mstepan.leetcode;

import java.util.Arrays;
import java.util.function.BooleanSupplier;

/** https://devtest2.hr.bookmap.com/task */
public class Bookmap {

    private static final char A = 'A';
    private static final char B = 'B';

    public static void main(String[] args) {
        int[] answers =
                findAnswerForQueries(
                        "ABBABAAB",
                        new Query[] {new Query(1, 4, 4), new Query(2, 6, 1), new Query(3, 7, 5)});

        System.out.println(Arrays.toString(answers));
    }

    public record Query(int left, int right, int k) {
        public Query {
            checkPrecondition(() -> left >= 1, "'left' is below 1");
            checkPrecondition(() -> right >= 1, "'right' is below 1");
            checkPrecondition(() -> k >= 1, "'k' is below 1");

            checkPrecondition(() -> left <= right, "left > right");
            checkPrecondition(
                    () -> k <= (left + (right - left)),
                    String.format(
                            "k = %s is not withing [%d...%d] range",
                            k, left, left + (right - left)));
        }

        int kIndexInStr() {
            return left + k - 2;
        }

        public int leftIdxInStr() {
            return left - 1;
        }

        private int rightIdxInStr() {
            return right - 1;
        }

        public int findKthCharacter(
                char searchCh, int expectedCounter, char[] chars, int[] prefixSum) {

            int from = leftIdxInStr();
            final int initialFrom = from;
            int to = rightIdxInStr();

            int maxPossibleCounter = prefixSum[to] - (from == 0 ? 0 : prefixSum[from - 1]);

            // short circuit early if the maximum possible number of occurrences for 'searchCh' is
            // less than 'expectedCounter'
            if (maxPossibleCounter < expectedCounter) {
                return -1;
            }

            // binary search the answer, time: O(lgN)
            // We can use binary-search the answer idea here with prefix sum.
            while (from <= to) {
                final int mid = from + (to - from) / 2;
                final int curCounter =
                        prefixSum[mid] - (initialFrom == 0 ? 0 : prefixSum[initialFrom - 1]);

                if (curCounter < expectedCounter) {
                    // search to the right
                    from = mid + 1;
                } else if (curCounter > expectedCounter) {
                    // search to the left
                    to = mid - 1;
                } else {
                    if (chars[mid] == searchCh) {
                        // result found
                        return mid - initialFrom + 1;
                    } else {
                        // IMPORTANT: search to the left using binary search,
                        // if we will use linear search from 'mid' position to the left, this cab be
                        // time: O(N)
                        to = mid - 1;
                    }
                }
            }

            // linear search, time: O(N)
            //            int actualCounter = 0;
            //
            //            for (int i = leftIdxInStr(), offset = 1; i <= rightIdxInStr(); ++i,
            // ++offset) {
            //                if (chars[i] == searchCh) {
            //                    ++actualCounter;
            //                }
            //
            //                if (actualCounter == expectedCounter) {
            //                    return offset;
            //                }
            //            }

            return -1;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d, %d)", left, right, k);
        }

        public boolean isValidForRange(char[] chars) {
            return leftIdxInStr() < chars.length && rightIdxInStr() < chars.length;
        }
    }

    /**
     * N = str.length()
     *
     * <p>M = queries.length
     *
     * <p>Time: (M*lgN), Space: O(N)
     */
    public static int[] findAnswerForQueries(String str, Query[] queries) {
        checkPrecondition(() -> str != null, "null 'str' detected");
        checkPrecondition(() -> queries != null, "null 'queries' detected");

        final char[] chars = str.toCharArray();

        checkPrecondition(
                () -> {
                    for (char singleCh : chars) {
                        if (singleCh != A && singleCh != B) {
                            return false;
                        }
                    }

                    return true;
                },
                "Incorrect character detected in 'str'");

        if (queries.length == 0) {
            return new int[0];
        }

        // time: O(N), space: O(N)
        final int[] aPrefixSum = calculatePrefixSum(chars, A);

        // time: O(N), space: O(N)
        final int[] bPrefixSum = calculatePrefixSum(chars, B);

        final int[] answers = new int[queries.length];

        // time: O(M*lgN)
        for (int i = 0; i < queries.length; i++) {
            final Query query = queries[i];

            checkPrecondition(() -> query.isValidForRange(chars), "query not valid for range");

            // time: O(1)
            int x = calculateX(chars, query, aPrefixSum, bPrefixSum);

            char charToSearch = chars[query.kIndexInStr()] == A ? B : A;
            int[] prefixSum = chars[query.kIndexInStr()] == A ? bPrefixSum : aPrefixSum;

            // time: O(lgN)
            answers[i] = query.findKthCharacter(charToSearch, x, chars, prefixSum);
        }

        return answers;
    }

    private static void checkPrecondition(BooleanSupplier precondition, String errorMessage) {
        if (!precondition.getAsBoolean()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static int[] calculatePrefixSum(char[] chars, char baseCh) {
        final int[] prefixSumArr = new int[chars.length];
        int prefixSum = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == baseCh) {
                ++prefixSum;
            }

            prefixSumArr[i] = prefixSum;
        }

        return prefixSumArr;
    }

    private static int calculateX(char[] chars, Query query, int[] aPrefixSum, int[] bPrefixSum) {
        int kIdx = query.kIndexInStr();
        int leftIdx = query.leftIdxInStr();

        if (chars[kIdx] == A) {
            return aPrefixSum[kIdx] - (leftIdx == 0 ? 0 : aPrefixSum[leftIdx - 1]);
        } else {
            return bPrefixSum[kIdx] - (leftIdx == 0 ? 0 : bPrefixSum[leftIdx - 1]);
        }
    }
}
