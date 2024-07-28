package com.github.mstepan.leetcode.hard;

import java.util.Arrays;
import java.util.Objects;

/**
 * 2262. Total Appeal of A String
 *
 * <p>https://leetcode.com/problems/total-appeal-of-a-string/
 */
public class TotalAppealOfString {

    /**
     * N = str.length() K = ('z' - 'a' + 1) = 26
     *
     * <p>time: O(N)
     *
     * <p>space: O(K)
     */
    public long appealSum(String str) {
        Objects.requireNonNull(str, "null 'str' detected");
        long appealCount = 0;

        int[] lastPos = new int['z' - 'a' + 1];
        Arrays.fill(lastPos, -1);
        long right = str.length() - 1;

        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            assert ch >= 'a' && ch <= 'z';

            long left = lastPos[ch - 'a'] + 1;

            appealCount += (i - left + 1) * (right - i + 1);

            lastPos[ch - 'a'] = i;
        }

        return appealCount;
    }
}
