package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SearchInRotatedSortedArray2Test {

    @Test
    void searchCase1() {
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 2));
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 5));
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 6));
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 0));
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 1));

        assertFalse(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 3));
        assertFalse(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, -1));
        assertFalse(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 4));
        assertFalse(SearchInRotatedSortedArray2.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 10));
    }

    @Test
    void searchCase2() {
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {1, 0, 1, 1, 1}, 0));
        assertTrue(SearchInRotatedSortedArray2.search(new int[] {1, 0, 1, 1, 1}, 1));
    }

    @Test
    void searchCase3() {
        assertTrue(
                SearchInRotatedSortedArray2.search(
                        new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 1));
        assertTrue(
                SearchInRotatedSortedArray2.search(
                        new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 2));

        assertFalse(
                SearchInRotatedSortedArray2.search(
                        new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 0));
        assertFalse(
                SearchInRotatedSortedArray2.search(
                        new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1}, 3));
    }
}
