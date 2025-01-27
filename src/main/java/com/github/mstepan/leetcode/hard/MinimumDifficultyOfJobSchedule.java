package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 1335. Minimum Difficulty of a Job Schedule
 *
 * <p>https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/description/
 */
public class MinimumDifficultyOfJobSchedule {

    /**
     * Use bottom-up dynamic programming approach to find optimal solution.
     *
     * <p>N = jobs.length, K = days
     *
     * <p>time: O(N^2 * K)
     *
     * <p>space: O(N)
     */
    public static int minDifficulty(int[] jobs, int days) {
        Objects.requireNonNull(jobs);
        assert days > 0;

        if (jobs.length < days) {
            return -1;
        }

        int[] opt = new int[jobs.length];

        opt[0] = jobs[0];

        for (int i = 1; i < jobs.length; i++) {
            opt[i] = Math.max(jobs[i], opt[i - 1]);
        }

        for (int k = 2; k <= days; k++) {
            int[] newOpt = new int[opt.length];

            for (int i = 0; i < newOpt.length; i++) {
                if (i + 1 < k) {
                    newOpt[i] = Integer.MAX_VALUE;
                } else {
                    int bestCur = Integer.MAX_VALUE;
                    int maxPrefix = 0;

                    for (int j = i; j >= k - 1; --j) {
                        maxPrefix = Math.max(maxPrefix, jobs[j]);

                        bestCur = Math.min(bestCur, maxPrefix + opt[j - 1]);
                    }

                    newOpt[i] = bestCur;
                }
            }

            opt = newOpt;
        }

        return opt[opt.length - 1];
    }
}
