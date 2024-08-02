package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 3147. Taking Maximum Energy From the Mystic Dungeon
 *
 * <p>https://leetcode.com/problems/taking-maximum-energy-from-the-mystic-dungeon/
 */
public class TakingMaximumEnergyFromTheMysticDungeon {

    /**
     * time: O(N)
     *
     * <p>space: O(K)
     */
    public int maximumEnergy(int[] energy, int k) {
        Objects.requireNonNull(energy);
        if (k < 1) {
            throw new IllegalArgumentException("k < 1, k = " + k);
        }

        if (energy.length == 0) {
            return 0;
        }

        if (k >= energy.length) {
            return findMax(energy);
        }

        int[] bucketsSum = new int[k];

        for (int i = 0; i < energy.length; ++i) {
            if (bucketsSum[i % k] < 0) {
                bucketsSum[i % k] = energy[i];
            } else {
                bucketsSum[i % k] += energy[i];
            }
        }

        return findMax(bucketsSum);
    }

    private int findMax(int[] arr) {
        assert arr != null;
        assert arr.length > 0;

        int maxValue = arr[0];

        for (int val : arr) {
            maxValue = Math.max(maxValue, val);
        }

        return maxValue;
    }
}
