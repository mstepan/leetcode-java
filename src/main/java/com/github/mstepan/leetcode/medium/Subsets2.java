package com.github.mstepan.leetcode.medium;

import java.util.*;
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
