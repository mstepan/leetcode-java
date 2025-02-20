package com.github.mstepan.leetcode.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 664. Strange Printer
 *
 * <p>https://leetcode.com/problems/strange-printer/
 */
public class StrangePrinter {

    /**
     * Use top-down dynamic programming with memoization.
     *
     * <p>N = str.length(), max value = 100
     *
     * <p>K = 'z' - 'a' + 1 = 26
     *
     * <p>time: O(N^2 * K) = 100 * 100 * 26 = 260_000
     *
     * <p>space: O(N^2 * K)
     */
    public static int strangePrinter(String str) {
        Objects.requireNonNull(str, "str is null");

        if (str.length() < 2) {
            return str.length();
        }

        char[] arr = str.toCharArray();

        return optRec(arr, 0, arr.length - 1, '\0', new HashMap<>());
    }

    private static int optRec(
            char[] arr, int from, int to, char midCh, Map<CachedResult, Integer> cache) {
        assert cache != null : "null 'cache' detected";

        CachedResult resKey = new CachedResult(from, to, midCh);

        Integer cachedResult = cache.get(resKey);

        if (cachedResult != null) {
            return cachedResult;
        }

        int result;

        if (arr[from] == arr[to]) {
            char baseCh = arr[from];

            int left = from + 1;
            while (left <= to && arr[left] == baseCh) {
                ++left;
            }

            int right = to - 1;
            while (right >= from && arr[right] == baseCh) {
                --right;
            }

            result = 1 + (left > right ? 0 : optRec(arr, left, right, baseCh, cache));

        } else {
            int left = from + 1;
            while (left < arr.length && arr[left] == arr[from]) {
                ++left;
            }

            int right = to - 1;
            while (right >= 0 && arr[right] == arr[to]) {
                --right;
            }

            int bestSol1 = (arr[from] == midCh ? 0 : 1) + optRec(arr, left, to, midCh, cache);

            int bestSol2 = (arr[to] == midCh ? 0 : 1) + optRec(arr, from, right, midCh, cache);

            result = Math.min(bestSol1, bestSol2);
        }

        cache.put(resKey, result);
        return result;
    }

    private static final class CachedResult {
        final int from;
        final int to;
        char midCh;

        CachedResult(int from, int to, char midCh) {
            this.from = from;
            this.to = to;
            this.midCh = midCh;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CachedResult that = (CachedResult) obj;
            return from == that.from && to == that.to && midCh == that.midCh;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, midCh);
        }
    }
}
