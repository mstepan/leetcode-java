package com.github.mstepan.leetcode.medium;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 1985. Find the Kth Largest Integer in the Array
 *
 * <p>https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/description/
 */
public class FindTheKthLargestIntegerInTheArray {

    private static final Comparator<String> STR_AS_NUM_ASC =
            (first, second) -> {
                int lengthCmp = Integer.compare(first.length(), second.length());
                if (lengthCmp != 0) {
                    return lengthCmp;
                }

                assert first.length() == second.length();

                for (int i = 0; i < first.length(); ++i) {
                    int charCmp = Character.compare(first.charAt(i), second.charAt(i));
                    if (charCmp != 0) {
                        return charCmp;
                    }
                }

                return 0;
            };

    /**
     * time: O(N*lgK), worst case O(N*lgN)
     *
     * <p>space: O(K), worst caseO(N)
     */
    public String kthLargestNumber(String[] nums, int k) {
        Objects.requireNonNull(nums, "null 'nums' detected");
        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException(
                    "Incorrect 'k', expected in range [1.."
                            + nums.length
                            + "], but found k = "
                            + k);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(STR_AS_NUM_ASC);

        for (int i = 0; i < k; ++i) {
            minHeap.add(nums[i]);
        }

        for (int i = k; i < nums.length; ++i) {

            assert !minHeap.isEmpty();

            String lastMin = minHeap.peek();

            if (STR_AS_NUM_ASC.compare(nums[i], lastMin) > 0) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        assert !minHeap.isEmpty();
        return minHeap.poll();
    }
}
