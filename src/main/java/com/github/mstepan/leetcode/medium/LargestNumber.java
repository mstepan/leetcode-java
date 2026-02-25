package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 179. Largest Number
 *
 * <p>https://leetcode.com/problems/largest-number/description/
 */
public class LargestNumber {

    private static final Comparator<String> ALPHABETIC_STR_SPECIAL_ASC =
            (first, second) -> {
                String firstSecond = first + second;
                String secondFirst = second + first;

                return firstSecond.compareTo(secondFirst);
            };

    private static final Comparator<String> ALPHABETIC_STR_SPECIAL_DESC =
            ALPHABETIC_STR_SPECIAL_ASC.reversed();

    public static String largestNumber(int[] nums) {
        Objects.requireNonNull(nums, "nums cannot be null");

        String[] numsStr = toStringArray(nums);

        Arrays.sort(numsStr, ALPHABETIC_STR_SPECIAL_DESC);

        return concatenateSkippingLeadingZeros(numsStr);
    }

    private static String[] toStringArray(int[] nums) {
        assert nums != null;
        String[] arr = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        return arr;
    }

    private static String concatenateSkippingLeadingZeros(String[] numsStr) {
        assert numsStr != null;
        StringBuilder res = new StringBuilder(numsStr.length * 5); // 1000000

        for (String valueAsStr : numsStr) {
            if (res.isEmpty() && valueAsStr.equals("0")) {
                continue;
            }
            res.append(valueAsStr);
        }

        if (res.isEmpty()) {
            return "0";
        }
        return res.toString();
    }
}
