package com.github.mstepan.leetcode.hard;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
            median.add(i, nums[i]);
        }

        res[0] = median.median();

        for (int i = 1; i < res.length; ++i) {
            int leftElem = nums[i - 1];
            median.remove(i - 1, leftElem);

            int rightElem = nums[i + k - 1];
            median.add(i + k - 1, rightElem);

            res[i] = median.median();
        }

        return res;
    }

    static class MovingMedian {

        enum HeapType {
            MAX_HEAP,
            MIN_HEAP
        }

        static final class HeapSlot {
            final int initialArrIdx;
            final int value;
            int heapIdx;
            HeapType heapType;

            public HeapSlot(int initialArrIdx, int value) {
                this.initialArrIdx = initialArrIdx;
                this.value = value;
                this.heapIdx = -1; // not in heap yet
            }

            String key() {
                return "%d:%d".formatted(initialArrIdx, value);
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }

                HeapSlot other = (HeapSlot) obj;
                return value == other.value && initialArrIdx == other.initialArrIdx;
            }

            @Override
            public int hashCode() {
                return 31 * initialArrIdx + value;
            }

            @Override
            public String toString() {
                return "%d (%d)".formatted(value, initialArrIdx);
            }
        }

        final Map<String, HeapSlot> heapSlots = new HashMap<>();

        final int windowSize;

        final HeapSlot[] maxHeap;
        int maxHeapSize;

        final HeapSlot[] minHeap;
        int minHeapSize;

        MovingMedian(int windowSize) {
            if (windowSize <= 0) {
                throw new IllegalArgumentException("Window size must be greater than zero");
            }
            this.windowSize = windowSize;
            this.maxHeap = new HeapSlot[windowSize / 2 + 2];
            this.minHeap = new HeapSlot[maxHeap.length];
        }

        void add(int initialArrIdx, int value) {
            if (maxHeapSize == 0 || maxHeap[0].value >= value) {
                addToHeap(maxHeap, new HeapSlot(initialArrIdx, value));
            } else {
                addToHeap(minHeap, new HeapSlot(initialArrIdx, value));
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

        private void addToHeap(HeapSlot[] heap, HeapSlot slot) {
            boolean isMaxHeap = heap == maxHeap;

            heap[isMaxHeap ? maxHeapSize : minHeapSize] = slot;
            slot.heapType = isMaxHeap ? HeapType.MAX_HEAP : HeapType.MIN_HEAP;
            slot.heapIdx = isMaxHeap ? maxHeapSize : minHeapSize;
            heapSlots.put(slot.key(), slot);

            if (isMaxHeap) {
                ++maxHeapSize;
            } else {
                ++minHeapSize;
            }

            // fix up max heap property
            fixUp(heap, isMaxHeap ? maxHeapSize - 1 : minHeapSize - 1);
        }

        /** Fix UP max or min heap from idx. */
        private void fixUp(HeapSlot[] heap, int start_idx) {
            final boolean isMaxHeap = (heap == maxHeap);

            int idx = start_idx;
            while (idx != 0) {
                int parentIdx = parentIdx(idx);

                if (isMaxHeap) {
                    if (heap[parentIdx].value >= heap[idx].value) {
                        break;
                    }
                } else {
                    if (heap[parentIdx].value <= heap[idx].value) {
                        break;
                    }
                }

                swap(heap, idx, parentIdx);
                idx = parentIdx;
            }
        }

        /** Fix DOWN max or min heap from idx. */
        private void fixDown(HeapSlot[] heap, int start_idx) {
            final boolean isMaxHeap = (heap == maxHeap);

            int idx = start_idx;
            while (true) {
                int childIdx1 = childIdx1(idx);
                int childIdx2 = childIdx2(idx);

                if (isMaxHeap) {
                    int maxIdx = idx;

                    if (childIdx1 < maxHeapSize
                            && maxHeap[childIdx1].value > maxHeap[maxIdx].value) {
                        maxIdx = childIdx1;
                    }

                    if (childIdx2 < maxHeapSize
                            && maxHeap[childIdx2].value > maxHeap[maxIdx].value) {
                        maxIdx = childIdx2;
                    }

                    if (maxIdx == idx) {
                        break;
                    }

                    swap(maxHeap, maxIdx, idx);

                    idx = maxIdx;
                } else {
                    int minIdx = idx;

                    if (childIdx1 < minHeapSize
                            && minHeap[childIdx1].value < minHeap[minIdx].value) {
                        minIdx = childIdx1;
                    }

                    if (childIdx2 < minHeapSize
                            && minHeap[childIdx2].value < minHeap[minIdx].value) {
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

        private static void swap(HeapSlot[] arr, int from, int to) {
            if (from == to) {
                return;
            }

            HeapSlot temp = arr[from];
            arr[from] = arr[to];
            arr[to] = temp;

            arr[from].heapIdx = from;
            arr[to].heapIdx = to;
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

        private void moveOneElement(HeapSlot[] fromHeap, HeapSlot[] toHeap) {
            final boolean isMaxHeap = (fromHeap == maxHeap);
            final HeapSlot elemToMove = fromHeap[0];

            // Remove element from the heap
            if (isMaxHeap) {
                maxHeap[0] = maxHeap[maxHeapSize - 1];
                maxHeap[0].heapIdx = 0;

                maxHeap[maxHeapSize - 1] = null;
                --maxHeapSize;
            } else {
                minHeap[0] = minHeap[minHeapSize - 1];
                minHeap[0].heapIdx = 0;

                minHeap[minHeapSize - 1] = null;
                --minHeapSize;
            }

            fixDown(fromHeap, 0);

            // add element to the other heap
            addToHeap(toHeap, elemToMove);
        }

        void remove(int initialArrIdx, int valueToRemove) {
            // search and remove from max heap

            // TODO: error here

            HeapSlot foundSlot = findValueIdx(new HeapSlot(initialArrIdx, valueToRemove));
            assert foundSlot != null : "Value not found in max heap: " + valueToRemove;
            int i = foundSlot.heapIdx;

            if (foundSlot.heapType == HeapType.MAX_HEAP) {
                maxHeap[i] = maxHeap[maxHeapSize - 1];
                maxHeap[i].heapIdx = i;

                maxHeap[maxHeapSize - 1] = null;
                --maxHeapSize;

                if (i < maxHeapSize) {
                    if (maxHeap[i].value > valueToRemove) {
                        fixUp(maxHeap, i);
                    } else {
                        fixDown(maxHeap, i);
                    }
                }
            } else {
                minHeap[i] = minHeap[minHeapSize - 1];
                minHeap[i].heapIdx = i;

                minHeap[minHeapSize - 1] = null;
                --minHeapSize;

                if (i < minHeapSize) {
                    if (minHeap[i].value < valueToRemove) {
                        fixUp(minHeap, i);
                    } else {
                        fixDown(minHeap, i);
                    }
                }
            }

            rebalance();
        }

        /*
        Use HashMap to find value in O(1) for deletion
         */
        private HeapSlot findValueIdx(HeapSlot searchValue) {
            HeapSlot foundSlot = heapSlots.get(searchValue.key());
            assert foundSlot != null;

            return foundSlot;
        }

        double median() {
            if (maxHeapSize == 0 && minHeapSize == 0) {
                throw new IllegalStateException("No elements available to calculate median");
            }

            // max heap always bigger or equals to min heap
            if (maxHeapSize > minHeapSize) {
                return maxHeap[0].value;
            }

            BigDecimal first = BigDecimal.valueOf(maxHeap[0].value, 5);
            BigDecimal second = BigDecimal.valueOf(minHeap[0].value, 5);

            BigDecimal res =
                    first.add(second).divide(BigDecimal.valueOf(2, 5), RoundingMode.HALF_UP);

            return res.doubleValue();
        }
    }
}
