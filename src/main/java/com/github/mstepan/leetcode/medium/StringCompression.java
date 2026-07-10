package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 443. String Compression
 *
 * <p>https://leetcode.com/problems/string-compression/
 */
public class StringCompression {

    /**
     * time: O(N)
     *
     * <p>space: O(1), in-place compression
     */
    public static int compress(char[] data) {
        Objects.requireNonNull(data);

        if (data.length < 2) {
            return data.length;
        }

        int insertIdx = 0;

        char last = data[0];
        int cnt = 1;

        for (int i = 1; i < data.length; ++i) {
            if (data[i] == last) {
                ++cnt;
            } else {
                insertIdx = encode(last, cnt, data, insertIdx);
                last = data[i];
                cnt = 1;
            }
        }

        // encode last character or sequence of characters
        insertIdx = encode(last, cnt, data, insertIdx);

        return insertIdx;
    }

    private static int encode(char last, int count, char[] data, int insertIdx) {

        data[insertIdx] = last;
        ++insertIdx;

        if (count != 1) {
            // 123 == encoded as ==> ['3', '2', '1']
            // ['3', '2', '1'] == reversed ==> ['1', '2', '3']

            final int digitStartIdx = insertIdx;

            while (count != 0) {
                data[insertIdx] = (char) ('0' + (count % 10));

                ++insertIdx;
                count /= 10;
            }

            reverse(data, digitStartIdx, insertIdx - 1);
        }

        return insertIdx;
    }

    private static void reverse(char[] data, int from, int to) {
        while (from < to) {
            char temp = data[from];
            data[from] = data[to];
            data[to] = temp;

            ++from;
            --to;
        }
    }
}
