package com.github.mstepan.leetcode.medium;

import java.util.*;

/**
 * 1718. Construct the Lexicographically Largest Valid Sequence
 *
 * <p>https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
 */
public class ConstructLexicographicallyLargestValidSequence {

    public static int[] constructDistancedSequence(int n) {
        checkInRange(n, 1, 20);

        final List<int[]> best = new ArrayList<>();
        tryPlaceBest(new int[n * 2 - 1], 0, new HashSet<>(), n, best);

        return best.getFirst();
    }

    private static void tryPlaceBest(
            int[] solution, int idx, Set<Integer> alreadyPlaced, int elemsCount, List<int[]> best) {

        if (alreadyPlaced.size() == elemsCount) {
            best.add(Arrays.copyOf(solution, solution.length));
            return;
        }

        for (int value = elemsCount; value >= 1 && best.isEmpty(); --value) {

            if (!alreadyPlaced.contains(value)) {

                if (value == 1) {
                    solution[idx] = 1;
                    alreadyPlaced.add(1);

                    tryPlaceBest(
                            solution,
                            findEmptySlotFrom(idx + 1, solution),
                            alreadyPlaced,
                            elemsCount,
                            best);

                    alreadyPlaced.remove(1);
                    solution[idx] = 0;
                } else {
                    if (idx + value < solution.length && solution[idx + value] == 0) {
                        solution[idx] = value;
                        solution[idx + value] = value;
                        alreadyPlaced.add(value);

                        tryPlaceBest(
                                solution,
                                findEmptySlotFrom(idx, solution),
                                alreadyPlaced,
                                elemsCount,
                                best);

                        alreadyPlaced.remove(value);
                        solution[idx + value] = 0;
                        solution[idx] = 0;
                    }
                }
            }
        }
    }

    private static int findEmptySlotFrom(int from, int[] sol) {
        for (int i = from; i < sol.length; i++) {
            if (sol[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private static void checkInRange(int value, int from, int to) {
        if (value < from || value > to) {
            throw new IllegalArgumentException(
                    String.format("value=%d not in range [%d..%d]", value, from, to));
        }
    }
}
