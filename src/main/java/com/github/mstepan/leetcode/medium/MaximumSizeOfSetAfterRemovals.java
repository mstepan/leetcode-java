package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * 3002. Maximum Size of a Set After Removals
 *
 * <p>https://leetcode.com/problems/maximum-size-of-a-set-after-removals/description/
 */
public class MaximumSizeOfSetAfterRemovals {

    /**
     * Use greedy approach to delete values that maximize final combined set size.
     *
     * <p>time: O(N)
     *
     * <p>space: O(N)
     */
    public static int maximumSetSize(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1, "arr1 is null");
        Objects.requireNonNull(arr2, "arr2 is null");

        assert arr1.length == arr2.length;
        assert (arr1.length & 1) == 0;

        int half = arr1.length / 2;

        Set<Integer> first = new HashSet<>(Arrays.stream(arr1).boxed().toList());
        Set<Integer> second = new HashSet<>(Arrays.stream(arr2).boxed().toList());

        Set<Integer> common = intersection(first, second);

        while (first.size() > half || second.size() > half) {

            Set<Integer> bigger = (first.size() >= second.size()) ? first : second;

            if (common.isEmpty()) {
                // no common elements left, so just remove any
                removeOne(bigger);
            } else {
                // there is at least one common value between two sets, so remove common value
                int removedValue = removeOne(common);
                bigger.remove(removedValue);
            }
        }

        return combine(first, second).size();
    }

    private static Set<Integer> intersection(Set<Integer> first, Set<Integer> second) {
        assert first != null;
        assert second != null;

        Set<Integer> res = new HashSet<>();

        for (int val : first) {
            if (second.contains(val)) {
                res.add(val);
            }
        }

        return res;
    }

    private static Integer removeOne(Set<Integer> data) {
        assert data != null;
        assert !data.isEmpty();

        Iterator<Integer> it = data.iterator();

        assert it.hasNext();

        int value = it.next();
        it.remove();

        return value;
    }

    private static Set<Integer> combine(Set<Integer> first, Set<Integer> second) {
        assert first != null;
        assert second != null;

        Set<Integer> merged = new HashSet<>(first);
        merged.addAll(second);
        return merged;
    }
}
