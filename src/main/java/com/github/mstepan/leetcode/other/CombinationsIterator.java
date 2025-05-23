package com.github.mstepan.leetcode.other;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/** Iterator that generates all K combinations values from array of size N. */
public final class CombinationsIterator implements Iterator<int[]> {
    private final int[] arr;

    private final Range[] ranges;
    private final int[] offsets;

    public CombinationsIterator(int[] arr, int combinationSize) {
        this.arr = Objects.requireNonNull(arr);
        if (combinationSize <= 0 || combinationSize > arr.length) {
            throw new IllegalArgumentException(
                    "'combinationSize' must be between 0 and " + arr.length);
        }
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
        int[] value = new int[offsets.length];

        for (int i = 0; i < value.length; ++i) {
            int singleOffset = offsets[i];
            assert singleOffset >= 0 && singleOffset < arr.length;

            value[i] += arr[singleOffset];
        }

        return value;
    }

    private record Range(int from, int to) {
        Range {
            if (from > to) {
                throw new IllegalArgumentException("From > to: from = " + from + ", to = " + to);
            }
        }
    }
}
