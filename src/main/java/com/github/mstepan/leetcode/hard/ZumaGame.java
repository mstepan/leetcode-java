package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 488. Zuma Game
 *
 * <p>https://leetcode.com/problems/zuma-game/description
 */
public class ZumaGame {

    private static final Map<String, Integer> CACHE = new HashMap<>(2_000_000);

    public static int findMinStep(String board, String hand) {
        checkValidColorString(board, "Invalid 'board'");
        checkValidColorString(hand, "Invalid 'hand'");

        CACHE.clear();
        return optSolutionRec(board, hand, CACHE);
    }

    private static int optSolutionRec(String board, String hand, Map<String, Integer> cache) {
        assert board != null;
        assert hand != null;

        if (board.isEmpty()) {
            return 0;
        }

        if (hand.isEmpty()) {
            return -1;
        }

        final String cacheKey = board + ":" + hand;

        Integer solution = cache.get(cacheKey);

        if (solution != null) {
            return solution;
        }

        Set<Character> alreadyHandled = new HashSet<>();

        int bestSoFar = -1;

        for (int i = 0; i < hand.length(); ++i) {
            char handCh = hand.charAt(i);

            // insert only unique chars from HAND (duplicates will have similar effect)
            if (!alreadyHandled.contains(handCh)) {
                alreadyHandled.add(handCh);

                String handWithoutCh = removeCharAt(hand, i);

                // Use 'handCh' to insert
                List<Integer> insertPlaces = findInsertPlaces(board, handCh);

                // try to insert into already existed sequence of same colors
                for (int singlePlace : insertPlaces) {
                    String nextBoard = calculateNextBoard(board, singlePlace, handCh);
                    bestSoFar = recalculateForCurrent(bestSoFar, nextBoard, handWithoutCh, cache);
                }

                // try to insert in any arbitrary place
                for (int j = 0; j < board.length(); ++j) {
                    if (board.charAt(j) != handCh) {
                        String nextBoard = insertBefore(board, j, handCh);
                        bestSoFar =
                                recalculateForCurrent(bestSoFar, nextBoard, handWithoutCh, cache);
                    }
                }

                // insert as a last character
                if (board.charAt(board.length() - 1) != handCh) {
                    String nextBoard = board + handCh;
                    bestSoFar = recalculateForCurrent(bestSoFar, nextBoard, handWithoutCh, cache);
                }
            }
        }

        cache.put(cacheKey, bestSoFar);

        return bestSoFar;
    }

    private static int recalculateForCurrent(
            int bestSoFar, String nextBoard, String handWithoutCh, Map<String, Integer> cache) {
        int bestCur = optSolutionRec(nextBoard, handWithoutCh, cache);

        if (bestCur != -1) {
            bestCur += 1;

            if (bestSoFar == -1 || bestSoFar > bestCur) {
                return bestCur;
            }
        }

        return bestSoFar;
    }

    private static String insertBefore(String board, int idx, char handCh) {

        if (idx == 0) {
            return handCh + board;
        }

        return board.substring(0, idx) + handCh + board.substring(idx);
    }

    static String calculateNextBoard(String board, int insertPlace, char handCh) {
        assert board != null;
        assert insertPlace >= 0 && insertPlace < board.length();
        assert board.charAt(insertPlace) == handCh;

        int length = runLength(board, insertPlace);

        if (length > 1) {
            int left = insertPlace - 1;
            int right = insertPlace + length;

            while (left >= 0 && right < board.length()) {

                final int initialLeft = left;
                final int initialRight = right;

                char otherCh = board.charAt(right);
                int cnt = 0;

                while (left >= 0 && board.charAt(left) == otherCh) {
                    --left;
                    ++cnt;
                }

                while (right < board.length() && board.charAt(right) == otherCh) {
                    ++right;
                    ++cnt;
                }

                if (cnt < 3) {
                    return concatParts(board, 0, initialLeft, initialRight, board.length() - 1);
                }
            }

            if (left < 0 && right >= board.length()) {
                return "";
            }

            if (left < 0) {
                return board.substring(right);
            } else {
                return board.substring(0, left + 1);
            }

        } else {
            return concatParts(board, 0, insertPlace, insertPlace, board.length() - 1);
        }
    }

    private static String concatParts(String board, int from1, int to1, int from2, int to2) {
        return board.substring(from1, to1 + 1) + board.substring(from2, to2 + 1);
    }

    private static int runLength(String board, int insertPlace) {
        char baseCh = board.charAt(insertPlace);
        int length = 1;

        for (int i = insertPlace + 1; i < board.length(); ++i) {
            if (board.charAt(i) != baseCh) {
                break;
            }
            ++length;
        }

        return length;
    }

    // "WWRBBRWBBWWB" -> 0, 6, 9
    static List<Integer> findInsertPlaces(String board, char handCh) {
        assert board != null;
        assert COLORS.contains(handCh);

        List<Integer> insertPlaces = new ArrayList<>();

        char prev = ' ';

        for (int i = 0; i < board.length(); ++i) {
            char ch = board.charAt(i);

            if (prev != ch) {
                if (ch == handCh) {
                    insertPlaces.add(i);
                }
                prev = ch;
            }
        }

        return insertPlaces;
    }

    static String removeCharAt(String hand, int idx) {
        assert hand != null;
        assert idx >= 0 && idx < hand.length();

        if (idx == 0) {
            return hand.substring(idx + 1);
        }

        if (idx == hand.length() - 1) {
            return hand.substring(0, idx);
        }

        return hand.substring(0, idx) + hand.substring(idx + 1);
    }

    private static void checkValidColorString(String str, String errorMsg) {
        if (str == null) {
            throw new IllegalArgumentException("%s, str = null".formatted(errorMsg));
        }

        for (int i = 0; i < str.length(); i++) {
            if (!isColorChar(str.charAt(i))) {
                throw new IllegalArgumentException("%s, str = %s".formatted(errorMsg, str));
            }
        }
    }

    private static final Set<Character> COLORS = Set.of('R', 'Y', 'B', 'G', 'W');

    private static boolean isColorChar(char ch) {
        return COLORS.contains(ch);
    }
}
