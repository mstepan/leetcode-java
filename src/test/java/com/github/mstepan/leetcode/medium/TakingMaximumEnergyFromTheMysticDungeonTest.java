package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TakingMaximumEnergyFromTheMysticDungeonTest {

    @Test
    void maximumEnergySimpleCase() {
        assertEquals(
                3,
                new TakingMaximumEnergyFromTheMysticDungeon()
                        .maximumEnergy(new int[] {5, 2, -10, -5, 1}, 3));
    }

    @Test
    void maximumEnergyNegativeSum() {
        assertEquals(
                -1,
                new TakingMaximumEnergyFromTheMysticDungeon()
                        .maximumEnergy(new int[] {-2, -3, -1}, 2));
    }

    @Test
    void maximumEnergyEmptyArray() {
        assertEquals(
                0, new TakingMaximumEnergyFromTheMysticDungeon().maximumEnergy(new int[] {}, 2));
    }

    @Test
    void maximumEnergyStepBiggerThanArrayLength() {
        assertEquals(
                5,
                new TakingMaximumEnergyFromTheMysticDungeon()
                        .maximumEnergy(new int[] {5, 2, -10, -5, 1}, 10));
    }

    @Test
    void maximumEnergyWithZeroStepShouldFail() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TakingMaximumEnergyFromTheMysticDungeon()
                            .maximumEnergy(new int[] {5, 2, -10, -5, 1}, 0);
                });
    }

    @Test
    void maximumEnergyWithNegativeStepShouldFail() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new TakingMaximumEnergyFromTheMysticDungeon()
                            .maximumEnergy(new int[] {5, 2, -10, -5, 1}, -5);
                });
    }
}
