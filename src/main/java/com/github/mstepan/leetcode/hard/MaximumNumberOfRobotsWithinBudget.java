package com.github.mstepan.leetcode.hard;

import java.util.Objects;

/**
 * 2398. Maximum Number of Robots Within Budget
 * https://leetcode.com/problems/maximum-number-of-robots-within-budget/
 */
public class MaximumNumberOfRobotsWithinBudget {

    /** time: O(N*lgN) space: O(N) */
    public static int maximumRobots(int[] charges, int[] costs, long budget) {
        Objects.requireNonNull(charges, "null 'charges' detected");
        Objects.requireNonNull(costs, "null 'costs' detected");
        if (budget < 0L) {
            throw new IllegalArgumentException("'budget' can't be negative: " + budget);
        }
        if (charges.length != costs.length) {
            throw new IllegalArgumentException("charges.length != costs.length");
        }
        if (charges.length == 0) {
            return 0;
        }

        int from = 0;
        int to = 0;

        int maxLength = 0;

        long windowSum = costs[0];
        WindowMax windowMax = new WindowMax();
        windowMax.add(charges[0]);

        while (true) {
            long curBudget = windowMax.max() + (to - from + 1) * windowSum;

            if (curBudget <= budget) {
                maxLength = Math.max(maxLength, to - from + 1);
                ++to;

                if (to == costs.length) {
                    break;
                }

                windowSum += costs[to];
                windowMax.add(charges[to]);
            } else {
                windowSum -= costs[from];
                windowMax.remove(charges[from]);

                ++from;

                if (from == costs.length) {
                    break;
                }

                if (to < from) {
                    ++to;
                    windowSum += costs[to];
                    windowMax.add(charges[to]);
                }
            }
        }

        return maxLength;
    }

    /** Save sorted array if values. The max value is always at index 0. */
    public static class WindowMax {
        int[] arr;
        int[] count;

        int from;
        int size;

        public WindowMax() {
            this.arr = new int[16];
            this.count = new int[16];
            this.from = 0;
            this.size = 0;
        }

        /** time: O(lgN) */
        public void add(int value) {
            if (size == arr.length) {
                resize();
            }

            if (from + size <= arr.length) {
                // has 1 chunk
                int idx = findSmallerOrEqual(arr, from, from + size - 1, value);

                if (idx >= 0) {
                    // found
                    updateFirstChunk(idx, value);
                } else {
                    // smaller/equal value not found, that means that 'value' is smallest one
                    appendToEnd(value);
                }
            } else {
                // has 2 chunks in total
                int idx1 =
                        findSmallerOrEqual(
                                arr, from, Math.min(from + size - 1, arr.length - 1), value);

                if (idx1 >= 0) {
                    // found in 1st chunk
                    updateFirstChunk(idx1, value);
                    return;
                }

                final int firstChunkSize = arr.length - from;
                final int secondChunkSize = size - firstChunkSize;

                int idx2 = findSmallerOrEqual(arr, 0, secondChunkSize - 1, value);

                if (idx2 >= 0) {
                    // found in 2nd chunk
                    updateSecondChunk(idx2, value, firstChunkSize);
                } else {
                    // smaller/equal value not found in 2nd chunk, that means that 'value' is the
                    // smallest one
                    appendToEnd(value);
                }
            }
        }

        private void resize() {
            final int oldCapacity = arr.length;
            final int newCapacity = arr.length * 2;

            int[] tempArr = arr;
            int[] tempCount = count;

            arr = new int[newCapacity];
            count = new int[newCapacity];

            for (int copiedCount = 0, fromIdx = from;
                    copiedCount < size;
                    ++copiedCount, fromIdx = (fromIdx + 1) % oldCapacity) {

                arr[copiedCount] = tempArr[fromIdx];
                count[copiedCount] = tempCount[fromIdx];
            }

            from = 0;
        }

        private void updateFirstChunk(int idx, int searchValue) {
            if (arr[idx] == searchValue) {
                count[idx] += 1;
            } else {
                arr[idx] = searchValue;
                count[idx] = 1;
            }

            size = (idx - from) + 1;
        }

        private void appendToEnd(int value) {
            int insertionIdx = (from + size) % arr.length;

            arr[insertionIdx] = value;
            count[insertionIdx] = 1;
            size = size + 1;
        }

        private void updateSecondChunk(int idx2, int value, int firstChunkSize) {
            if (arr[idx2] == value) {
                count[idx2] += 1;
            } else {
                arr[idx2] = value;
                count[idx2] = 1;
                size = firstChunkSize + idx2 + 1;
            }
        }

        /**
         * Search for a value inside the array sorted in DESC order.
         *
         * <p>Example array: 20, 18, 15, 10, 9, 6,3,2
         *
         * <p>time: O(lgN)
         */
        private int findSmallerOrEqual(int[] arr, int fromInitial, int toInitial, int searchValue) {

            int foundIdx = -1;

            int lo = fromInitial;
            int hi = toInitial;

            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid] <= searchValue) {
                    foundIdx = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }

            return foundIdx;
        }

        /** time: O(1) */
        public void remove(int value) {
            if (size == 0) {
                throw new IllegalStateException("Can't remove value from an empty 'WindowMax'");
            }
            if (arr[from] == value) {
                count[from] -= 1;

                if (count[from] == 0) {
                    from = (from + 1) % arr.length;
                    size -= 1;
                }
            }
        }

        /** time: O(1) */
        public int max() {
            if (size == 0) {
                throw new IllegalStateException("Can't obtain max value from empty 'WindowMax'");
            }

            return arr[from];
        }
    }
}
