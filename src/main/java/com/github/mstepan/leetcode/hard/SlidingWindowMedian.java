package com.github.mstepan.leetcode.hard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 480. Sliding Window Median
 *
 * <p>https://leetcode.com/problems/sliding-window-median/description/
 */
public class SlidingWindowMedian {

    public static double[] medianSlidingWindow(int[] nums, int k) {
        Objects.requireNonNull(nums);

        if (k < 1 || k > nums.length) {
            throw new IllegalArgumentException("Invalid window size: " + k);
        }

        double[] res = new double[nums.length - k + 1];

        MovingMedian median = new MovingMedian(k);
        for (int i = 0; i < k; ++i) {
            median.add(nums[i]);
        }

        res[0] = median.median();

        for (int i = 1; i < res.length; ++i) {
            int leftElem = nums[i - 1];
            median.remove(leftElem);

            int rightElem = nums[i + k - 1];
            median.add(rightElem);

            res[i] = median.median();
        }

        return res;
    }

    static class MovingMedian {

        final int windowSize;

        final int[] maxHeap;
        int maxHeapSize;

        final int[] minHeap;
        int minHeapSize;

        MovingMedian(int windowSize) {
            if (windowSize <= 0) {
                throw new IllegalArgumentException("Window size must be greater than zero");
            }
            this.windowSize = windowSize;
            this.maxHeap = new int[windowSize / 2 + 2];
            this.minHeap = new int[maxHeap.length];
        }

        void add(int value) {
            if (maxHeapSize == 0 || maxHeap[0] >= value) {
                addToHeap(maxHeap, value);
            } else {
                addToHeap(minHeap, value);
            }

            rebalance();
        }

        private void rebalance() {
            int diff = maxHeapSize - minHeapSize;

            // max heap has 2 more elements
            if (diff == 2) {
                moveOneElement(maxHeap, minHeap);
            }
            // min heap has 1 more elements, we assume that max heap always bigger
            else if (diff == -1) {
                moveOneElement(minHeap, maxHeap);
            }
        }

        private void addToHeap(int[] heap, int value) {
            boolean isMaxHeap = heap == maxHeap;

            heap[isMaxHeap ? maxHeapSize : minHeapSize] = value;

            if (isMaxHeap) {
                ++maxHeapSize;
            } else {
                ++minHeapSize;
            }

            // fix up max heap property
            fixUp(heap, isMaxHeap ? maxHeapSize - 1 : minHeapSize - 1);
        }

        /** Fix UP max or min heap from idx. */
        private void fixUp(int[] heap, int start_idx) {
            final boolean isMaxHeap = (heap == maxHeap);

            int idx = start_idx;
            while (idx != 0) {
                int parentIdx = parentIdx(idx);

                if (isMaxHeap) {
                    if (heap[parentIdx] >= heap[idx]) {
                        break;
                    }
                } else {
                    if (heap[parentIdx] <= heap[idx]) {
                        break;
                    }
                }

                swap(heap, idx, parentIdx);
                idx = parentIdx;
            }
        }

        /** Fix DOWN max or min heap from idx. */
        private void fixDown(int[] heap, int start_idx) {
            final boolean isMaxHeap = (heap == maxHeap);

            int idx = start_idx;
            while (true) {
                int childIdx1 = childIdx1(idx);
                int childIdx2 = childIdx2(idx);

                if (isMaxHeap) {
                    int maxIdx = idx;

                    if (childIdx1 < maxHeapSize && maxHeap[childIdx1] > maxHeap[maxIdx]) {
                        maxIdx = childIdx1;
                    }

                    if (childIdx2 < maxHeapSize && maxHeap[childIdx2] > maxHeap[maxIdx]) {
                        maxIdx = childIdx2;
                    }

                    if (maxIdx == idx) {
                        break;
                    }

                    swap(maxHeap, maxIdx, idx);

                    idx = maxIdx;
                } else {
                    int minIdx = idx;

                    if (childIdx1 < minHeapSize && minHeap[childIdx1] < minHeap[minIdx]) {
                        minIdx = childIdx1;
                    }

                    if (childIdx2 < minHeapSize && minHeap[childIdx2] < minHeap[minIdx]) {
                        minIdx = childIdx2;
                    }

                    if (minIdx == idx) {
                        break;
                    }

                    swap(minHeap, minIdx, idx);

                    idx = minIdx;
                }
            }
        }

        private static void swap(int[] arr, int from, int to) {
            if (from == to) {
                return;
            }

            int temp = arr[from];
            arr[from] = arr[to];
            arr[to] = temp;
        }

        static int parentIdx(int idx) {
            return div2Ceil(idx) - 1;
        }

        private static int div2Ceil(int value) {
            return (value / 2) + ((value % 2 == 0) ? 0 : 1);
        }

        private static int childIdx1(int parentIdx) {
            return parentIdx * 2 + 1;
        }

        private static int childIdx2(int parentIdx) {
            return parentIdx * 2 + 2;
        }

        private void moveOneElement(int[] fromHeap, int[] toHeap) {
            final boolean isMaxHeap = (fromHeap == maxHeap);
            final int elemToMove = fromHeap[0];

            // Remove element from the heap
            if (isMaxHeap) {
                maxHeap[0] = maxHeap[maxHeapSize - 1];
                maxHeap[maxHeapSize - 1] = 0;
                --maxHeapSize;
            } else {
                minHeap[0] = minHeap[minHeapSize - 1];
                minHeap[minHeapSize - 1] = 0;
                --minHeapSize;
            }

            fixDown(fromHeap, 0);

            // add element to the other heap
            addToHeap(toHeap, elemToMove);
        }

        void remove(int valueToRemove) {
            // search and remove from max heap
            if (valueToRemove <= maxHeap[0]) {
                int i = findValueIdx(maxHeap, valueToRemove);
                assert i != -1 : "Value not found in max heap: " + valueToRemove;
                maxHeap[i] = maxHeap[maxHeapSize - 1];
                maxHeap[maxHeapSize - 1] = 0;
                --maxHeapSize;

                if (i < maxHeapSize) {
                    if (maxHeap[i] > valueToRemove) {
                        fixUp(maxHeap, i);
                    } else {
                        fixDown(maxHeap, i);
                    }
                }

            }
            // search and remove from min heap
            else {
                int i = findValueIdx(minHeap, valueToRemove);
                assert i != -1 : "Value not found in min  heap: " + valueToRemove;
                minHeap[i] = minHeap[minHeapSize - 1];
                minHeap[minHeapSize - 1] = 0;
                --minHeapSize;

                if (i < minHeapSize) {
                    if (minHeap[i] < valueToRemove) {
                        fixUp(minHeap, i);
                    } else {
                        fixDown(minHeap, i);
                    }
                }
            }

            rebalance();
        }

        /*
        TODO: We can use HashMap to quickly find elements by index instead of doing linear search in O(N) time.
         */
        private int findValueIdx(int[] heap, int searchValue) {
            int heapSize = heap == maxHeap ? maxHeapSize : minHeapSize;

            for (int i = 0; i < heapSize; i++) {
                if (heap[i] == searchValue) {

                    return i;
                }
            }

            return -1;
        }

        double median() {
            if (maxHeapSize == 0 && minHeapSize == 0) {
                throw new IllegalStateException("No elements available to calculate median");
            }

            // max heap always bigger or equals to min heap
            if (maxHeapSize > minHeapSize) {
                return maxHeap[0];
            }

            BigDecimal first = BigDecimal.valueOf(maxHeap[0], 5);
            BigDecimal second = BigDecimal.valueOf(minHeap[0], 5);

            BigDecimal res =
                    first.add(second).divide(BigDecimal.valueOf(2, 5), RoundingMode.HALF_UP);

            return res.doubleValue();
        }
    }
}
