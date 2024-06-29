package com.github.mstepan.leetcode.hard;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 895. Maximum Frequency Stack
 *
 * <p>https://leetcode.com/problems/maximum-frequency-stack/
 */
public class MaximumFrequencyStack {

    private final Map<Integer, Integer> freq = new HashMap<>();

    private final PriorityQueue<ValueWithFreq> maxHeap =
            new PriorityQueue<>(ValueWithFreq.FREQ_THEN_INSERTION_TIME_DESC);

    private int insertionTime;

    public MaximumFrequencyStack() {}

    /** time: O(lgN) */
    public void push(int val) {
        int valCnt = freq.compute(val, (key, cnt) -> cnt == null ? 1 : cnt + 1);
        maxHeap.add(new ValueWithFreq(val, valCnt, insertionTime));
        ++insertionTime;
    }

    /** time: O(lgN) */
    public int pop() {
        if (maxHeap.isEmpty()) {
            throw new IllegalStateException("Can't pop from EMPTY stack");
        }

        ValueWithFreq top = maxHeap.poll();
        freq.compute(
                top.val,
                (key, cnt) -> {
                    assert cnt != null;
                    return (cnt == 1) ? null : cnt - 1;
                });

        return top.val;
    }

    private static final class ValueWithFreq {

        private static final Comparator<ValueWithFreq> FREQ_THEN_INSERTION_TIME_DESC =
                Comparator.comparingInt(ValueWithFreq::getFreq)
                        .reversed()
                        .thenComparing(
                                Comparator.comparingInt(ValueWithFreq::getInsertionTime)
                                        .reversed());

        final int val;
        final int freq;

        final int insertionTime;

        ValueWithFreq(int val, int freq, int insertionTime) {
            this.val = val;
            this.freq = freq;
            this.insertionTime = insertionTime;
        }

        int getFreq() {
            return freq;
        }

        int getInsertionTime() {
            return insertionTime;
        }

        @Override
        public String toString() {
            return "(val=" + val + ", freq=" + freq + ", insertionTime=" + insertionTime + ")";
        }
    }
}
