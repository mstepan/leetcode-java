package com.github.mstepan.leetcode.medium;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

/**
 * 2592. Maximize Greatness of an Array
 *
 * <p>https://leetcode.com/problems/maximize-greatness-of-an-array/description/
 */
public class MaximizeGreatnessOfAnArray {

    /**
     * Use greedy approach and BS-tree.
     *
     * <p>time: O(N*lgN) space: O(N)
     */
    public static int maximizeGreatness(int[] arr) {
        Objects.requireNonNull(arr);

        NavigableMap<Integer, Integer> sortedValues = new TreeMap<>();

        for (int val : arr) {
            sortedValues.compute(val, (key, cnt) -> cnt == null ? 1 : cnt + 1);
        }

        int greatnessCnt = 0;

        for (int val : arr) {
            Map.Entry<Integer, Integer> candidate = sortedValues.higherEntry(val);

            if (candidate == null) {

                Map.Entry<Integer, Integer> smallest = sortedValues.pollFirstEntry();

                if (smallest.getValue() > 1) {
                    sortedValues.put(smallest.getKey(), smallest.getValue() - 1);
                }

            } else {
                ++greatnessCnt;

                if (candidate.getValue() == 1) {
                    sortedValues.remove(candidate.getKey());
                } else {
                    sortedValues.put(candidate.getKey(), candidate.getValue() - 1);
                }
            }
        }

        return greatnessCnt;
    }
}
