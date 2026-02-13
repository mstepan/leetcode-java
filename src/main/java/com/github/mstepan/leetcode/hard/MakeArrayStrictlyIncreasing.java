package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 1187. Make Array Strictly Increasing
 *
 * <p>https://leetcode.com/problems/make-array-strictly-increasing/
 */
public class MakeArrayStrictlyIncreasing {

    public static int makeArrayIncreasing(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1, "arr1 is null");
        Objects.requireNonNull(arr2, "arr2 is null");

        if (arr1.length < 2) {
            return 0;
        }

        // arr2 sorted and without duplicates
        List<Integer> candidates = sortAndRemoveDuplicates(arr2);

        Queue<SolutionState> queue = new ArrayDeque<>();
        queue.add(new SolutionState(Integer.MIN_VALUE, 0));

        for (int val : arr1) {

            List<SolutionState> nextLayer = new LinkedList<>();

            while (!queue.isEmpty()) {
                SolutionState state = queue.poll();

                if (val > state.prevValue) {
                    // keep current value
                    nextLayer.add(new SolutionState(val, state.cost));
                }

                // Replace current value with the next larger value from 'candidates' if available
                int idx = Collections.binarySearch(candidates, state.prevValue + 1);

                if (idx >= 0) {
                    nextLayer.add(new SolutionState(candidates.get(idx), state.cost + 1));
                } else {
                    int insertionIdx = (-idx) - 1;

                    if (insertionIdx < candidates.size()) {
                        nextLayer.add(
                                new SolutionState(candidates.get(insertionIdx), state.cost + 1));
                    }
                }
            }

            if (nextLayer.isEmpty()) {
                return -1;
            }

            // remove redundant states from 'nextLayer'
            nextLayer.sort(SolutionState.PREV_VALUE_ASC_COST_ASC_CMP);

            SolutionState prev = null;
            Iterator<SolutionState> it = nextLayer.iterator();

            while (it.hasNext()) {
                SolutionState cur = it.next();

                if (prev != null && prev.prevValue == cur.prevValue) {
                    it.remove();
                } else {
                    prev = cur;
                }
            }

            queue.addAll(nextLayer);
        }

        assert !queue.isEmpty();

        SolutionState best = queue.poll();

        while (!queue.isEmpty()) {
            SolutionState cur = queue.poll();
            best = (cur.cost < best.cost) ? cur : best;
        }

        return best.cost;
    }

    private static List<Integer> sortAndRemoveDuplicates(int[] arr) {
        Set<Integer> unique = new HashSet<>();

        for (int val : arr) {
            unique.add(val);
        }

        List<Integer> sorted = new ArrayList<>(unique);
        Collections.sort(sorted);

        return sorted;
    }

    record SolutionState(int prevValue, int cost) {

        private static final Comparator<SolutionState> PREV_VALUE_ASC_COST_ASC_CMP =
                Comparator.comparingInt(SolutionState::prevValue)
                        .thenComparing(SolutionState::cost);

        SolutionState {
            if (prevValue < 0 && prevValue != Integer.MIN_VALUE) {
                throw new IllegalArgumentException(
                        "Incorrect 'prevValue' detected, can be positive, zero or Integer.MIN_VALUE");
            }

            if (cost < 0) {
                throw new IllegalArgumentException("Negative cost detected");
            }
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", prevValue, cost);
        }
    }
}
