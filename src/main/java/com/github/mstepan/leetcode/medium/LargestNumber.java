package com.github.mstepan.leetcode.medium;

import java.util.Arrays;
import java.util.Objects;

/**
 * 179. Largest Number
 *
 * <p>https://leetcode.com/problems/largest-number/description/
 */
public class LargestNumber {

    public static String largestNumber(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            throw new IllegalArgumentException("'nums' is null or empty");
        }

        String[] numbersAsStrings =
                Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);

        Arrays.sort(
                numbersAsStrings, (first, second) -> (second + first).compareTo(first + second));

        if (numbersAsStrings.length == 0 || "0".equals(numbersAsStrings[0])) {
            return "0";
        }

        return String.join("", numbersAsStrings);
    }
}
