package com.github.mstepan.leetcode.hard;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 2035. Partition Array Into Two Arrays to Minimize Sum Difference ==> TLE
 *
 * <p>https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/description/
 */
public class PartitionArrayIntoTwoArraysToMinimizeSumDifference {

    public static int minimumDifference(int[] nums) {
        Objects.requireNonNull(nums);

        long minDiff = Long.MAX_VALUE;
        long totalSum = sum(nums);

        Iterator<int[]> it = new CombinationsIterator(nums, nums.length / 2);

        while (it.hasNext()) {
            int[] subArray = it.next();
            long leftSum = sum(subArray);
            long rightSum = totalSum - leftSum;

            minDiff = Math.min(minDiff, Math.abs(leftSum - rightSum));
        }

        return (int) minDiff;
    }

    private static long sum(int[] arr) {
        long sum = 0L;

        for (int val : arr) {
            sum += val;
        }

        return sum;
    }

    static final class CombinationsIterator implements Iterator<int[]> {
        private final int[] arr;
        private final int combinationSize;

        private final Range[] ranges;
        private final int[] offsets;

        public CombinationsIterator(int[] arr, int combinationSize) {
            this.arr = Objects.requireNonNull(arr);
            assert combinationSize > 0 && combinationSize <= arr.length;
            this.combinationSize = combinationSize;
            this.ranges = createRanges(arr, combinationSize);
            this.offsets = createOffsets(this.ranges);
        }

        private static Range[] createRanges(int[] arr, int combinationSize) {
            Range[] rangesArr = new Range[combinationSize];

            int to = arr.length - combinationSize;

            for (int i = 0; i < rangesArr.length; i++) {
                rangesArr[i] = new Range(i, to + i);
            }

            return rangesArr;
        }

        private static int[] createOffsets(Range[] rangesArr) {
            int[] offsets = new int[rangesArr.length];

            for (int i = 0; i < offsets.length; i++) {
                offsets[i] = rangesArr[i].from;
            }

            return offsets;
        }

        @Override
        public boolean hasNext() {
            return offsets[0] <= ranges[0].to;
        }

        @Override
        public int[] next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int[] value = createValueFromOffsets();

            moveOffsetToNext();

            return value;
        }

        private void moveOffsetToNext() {

            int idx = -1;

            for (int i = offsets.length - 1; i >= 0; i--) {
                if (offsets[i] < ranges[i].to) {
                    idx = i;
                    break;
                }
            }

            // Iterator fully completed, all offsets exhausted, move 0-offset by one to complete
            // this iterator
            if (idx == -1) {
                offsets[0] += 1;
            } else {
                for (int i = idx, last = offsets[idx] + 1; i < offsets.length; i++, ++last) {
                    offsets[i] = last;
                }
            }
        }

        private int[] createValueFromOffsets() {
            int[] value = new int[combinationSize];

            for (int i = 0; i < value.length; i++) {
                assert i < offsets.length;
                assert offsets[i] >= 0 && offsets[i] < arr.length;

                value[i] = arr[offsets[i]];
            }

            return value;
        }
    }

    private record Range(int from, int to) {
        Range {
            if (from > to) {
                throw new IllegalArgumentException("from > to");
            }
        }
    }
}
