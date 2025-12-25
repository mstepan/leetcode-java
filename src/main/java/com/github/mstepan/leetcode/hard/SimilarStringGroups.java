package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 839. Similar String Groups
 *
 * <p>https://leetcode.com/problems/similar-string-groups/description/
 */
public class SimilarStringGroups {

    public static int numSimilarGroups(String[] words) {
        Objects.requireNonNull(words);
        final int n = words.length;
        if (n <= 1) {
            return n;
        }

        UnionFind groups = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(words[i], words[j])) {
                    groups.union(i, j);
                }
            }
        }

        return groups.countSets();
    }

    // Two strings are similar if they are equal, or differ at exactly two positions with
    // cross-match.
    private static boolean isSimilar(String a, String b) {
        if (a == b) {
            return true; // same reference
        }
        int len = a.length();
        if (len != b.length()) {
            return false;
        }

        int first = -1, second = -1, mismatches = 0;

        for (int i = 0; i < len; i++) {
            char ca = a.charAt(i);
            char cb = b.charAt(i);
            if (ca != cb) {
                mismatches++;
                if (mismatches > 2) {
                    return false; // early exit
                }
                if (first == -1) {
                    first = i;
                } else {
                    second = i;
                }
            }
        }

        if (mismatches == 0) {
            return true;
        }
        if (mismatches != 2) {
            return false;
        }
        // cross-match check
        return a.charAt(first) == b.charAt(second) && a.charAt(second) == b.charAt(first);
    }

    private static final class UnionFind {
        // track parents for each set
        final int[] parent;

        // track size for each set
        final int[] size;
        int setsCount;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            setsCount = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            int cur = x;

            while (parent[cur] != cur) {
                cur = parent[cur];
            }
            return cur;
        }

        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) {
                return false;
            }
            if (size[ra] < size[rb]) {
                int t = ra;
                ra = rb;
                rb = t;
            }
            parent[rb] = ra;
            size[ra] += size[rb];
            setsCount--;
            return true;
        }

        int countSets() {
            return setsCount;
        }
    }
}
