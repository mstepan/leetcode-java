package com.github.mstepan.leetcode.medium;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 2860. Happy Students
 *
 * <p>https://leetcode.com/problems/happy-students/description/
 */
public class HappyStudents {

    /**
     * time: O(n*lgN)
     *
     * <p>space: O(1)
     */
    public static int countWays(List<Integer> nums) {
        Objects.requireNonNull(nums);

        if (nums.isEmpty()) {
            return 0;
        }

        Collections.sort(nums);

        int waysCount = 1;

        if (nums.get(0) != 0) {
            ++waysCount;
        }

        for (int i = 1; i < nums.size(); ++i) {
            int selectedSize = i;

            if (selectedSize > nums.get(i - 1) && selectedSize < nums.get(i)) {
                ++waysCount;
            }
        }

        return waysCount;
    }
}
