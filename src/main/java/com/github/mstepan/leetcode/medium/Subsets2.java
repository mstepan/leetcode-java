package com.github.mstepan.leetcode.medium;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * 90. Subsets II
 *
 * <p>https://leetcode.com/problems/subsets-ii/description/
 */
public class Subsets2 {

    /**
     * time: O(2^K + K*lgK)
     *
     * <p>space: O(2^K)
     *
     * <p>K = nums.length <= 10, K = 2^10 = 1024
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Objects.requireNonNull(nums);

        if (nums.length > 10) {
            throw new IllegalArgumentException("'nums' length exceed 10");
        }

        if (nums.length == 0) {
            return List.of(List.of());
        }

        // Sort input once, so we don't need to sort generated subsets to check duplicates
        Arrays.sort(nums);

        List<List<Integer>> allSubsets = new ArrayList<>();

        Set<String> duplicates = new HashSet<>();

        for (int mask = 0; mask < (1 << nums.length); ++mask) {

            List<Integer> subset = generateFromMask(nums, mask);

            String subKey = subset.stream().map(String::valueOf).collect(Collectors.joining(", "));

            if (duplicates.add(subKey)) {
                allSubsets.add(subset);
            }
        }

        return allSubsets;
    }

    public static List<List<Integer>> subsetsWithDupParallel(int[] nums) {

        Objects.requireNonNull(nums);

        if (nums.length > 10) {
            throw new IllegalArgumentException("'nums' length exceed 10");
        }

        if (nums.length == 0) {
            return List.of(List.of());
        }

        // Sort input once, so we don't need to sort generated subsets to check duplicates
        Arrays.sort(nums);

        try (var pool = new ForkJoinPool()) {
            int from = 0;
            int to = (1 << nums.length) - 1;

            return pool.submit(new SubsetGeneratorRecursiveTask(nums, from, to)).get();
        } catch (InterruptedException | ExecutionException ex) {
            if (ex instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            throw new IllegalStateException(ex);
        }
    }

    static class SubsetGeneratorRecursiveTask extends RecursiveTask<List<List<Integer>>> {

        private final int[] nums;
        private final int fromMask;
        private final int toMask;

        public SubsetGeneratorRecursiveTask(int[] nums, int fromMask, int toMask) {
            this.nums = nums;
            this.fromMask = fromMask;
            this.toMask = toMask;
        }

        @Override
        protected List<List<Integer>> compute() {

            int masksCount = toMask - fromMask + 1;

            if (masksCount <= 3) {
                // calculate sequentially
                return calculateForMaskRange();
            }

            int mid = fromMask + (toMask - fromMask) / 2;

            SubsetGeneratorRecursiveTask left =
                    new SubsetGeneratorRecursiveTask(nums, fromMask, mid);
            SubsetGeneratorRecursiveTask right =
                    new SubsetGeneratorRecursiveTask(nums, mid + 1, toMask);

            var rightTask = right.fork();

            var leftResult = left.compute();
            var rightRes = rightTask.join();

            // combine leftRes and rightRes subsets here, removing any cross-duplicates
            return combineWithoutDuplicates(leftResult, rightRes);
        }

        private List<List<Integer>> calculateForMaskRange() {
            List<List<Integer>> allSubsets = new ArrayList<>();

            Set<String> duplicates = new HashSet<>();

            for (int mask = fromMask; mask <= toMask; ++mask) {

                List<Integer> subset = generateFromMask(nums, mask);

                String subKey = subsetToStr(subset);

                if (duplicates.add(subKey)) {
                    allSubsets.add(subset);
                }
            }

            return allSubsets;
        }

        private List<List<Integer>> combineWithoutDuplicates(
                List<List<Integer>> left, List<List<Integer>> right) {

            List<List<Integer>> combined = new ArrayList<>(left.size() + right.size());

            combined.addAll(left);

            Set<String> duplicates = new HashSet<>();

            for (List<Integer> singleLeft : left) {
                duplicates.add(subsetToStr(singleLeft));
            }

            for (List<Integer> singleRight : right) {
                if (duplicates.add(subsetToStr(singleRight))) {
                    combined.add(singleRight);
                }
            }

            return combined;
        }

        private static String subsetToStr(List<Integer> subset) {
            return subset.stream().map(String::valueOf).collect(Collectors.joining(", "));
        }
    }

    private static List<Integer> generateFromMask(int[] nums, int mask) {
        assert nums != null;
        assert mask >= 0;

        List<Integer> subset = new ArrayList<>();

        for (int offset = 1, idx = 0; offset <= mask && idx < nums.length; offset <<= 1, ++idx) {
            if ((mask & offset) != 0) {
                subset.add(nums[idx]);
            }
        }

        return subset;
    }
}
