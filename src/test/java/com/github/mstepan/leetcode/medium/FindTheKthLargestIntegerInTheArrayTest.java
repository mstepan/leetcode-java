package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindTheKthLargestIntegerInTheArrayTest {

    @Test
    void findKthLargestNormalCase1() {

        // "3", "6", "7", "10"
        assertEquals(
                "10",
                new FindTheKthLargestIntegerInTheArray()
                        .kthLargestNumber(new String[] {"3", "6", "7", "10"}, 1));

        assertEquals(
                "7",
                new FindTheKthLargestIntegerInTheArray()
                        .kthLargestNumber(new String[] {"3", "6", "7", "10"}, 2));

        assertEquals(
                "6",
                new FindTheKthLargestIntegerInTheArray()
                        .kthLargestNumber(new String[] {"3", "6", "7", "10"}, 3));

        assertEquals(
                "3",
                new FindTheKthLargestIntegerInTheArray()
                        .kthLargestNumber(new String[] {"3", "6", "7", "10"}, 4));
    }

    @Test
    void findKthLargestNormalCase2() {

        //  "9", "15", "27", "35", "45", "67", "88", "122"
        // "15", "122", "9", "27", "35", "88", "67", "45"
        String[] sortedArr = new String[] {"9", "15", "27", "35", "45", "67", "88", "122"};

        for (int i = 0; i < sortedArr.length; ++i) {

            int idx = i + 1;

            assertEquals(
                    sortedArr[sortedArr.length - idx],
                    new FindTheKthLargestIntegerInTheArray()
                            .kthLargestNumber(
                                    new String[] {"15", "122", "9", "27", "35", "88", "67", "45"},
                                    idx));
        }
    }
}
