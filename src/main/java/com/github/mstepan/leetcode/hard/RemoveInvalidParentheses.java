package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 *
 * <p>https://leetcode.com/problems/remove-invalid-parentheses
 */
public class RemoveInvalidParentheses {

    /** Uses recursive backtracking with pruning to remove the invalid partial solutions earlier. */
    public static List<String> removeInvalidParentheses(String str) {
        Objects.requireNonNull(str, "null 'str' found");

        Set<String> allFoundSolutions = new HashSet<>();

        generateRecursively(str.toCharArray(), 0, 0, 0, new StringBuilder(), allFoundSolutions);

        return new ArrayList<>(allFoundSolutions);
    }

    private static void generateRecursively(
            char[] data,
            int idx,
            int leftCnt,
            int rightCnt,
            StringBuilder partialSol,
            Set<String> allFoundSolutions) {

        if (rightCnt > leftCnt) {
            return;
        }

        if (idx >= data.length) {
            if (leftCnt == rightCnt) {
                String curSol = partialSol.toString();

                String prevSol =
                        allFoundSolutions.isEmpty() ? "" : allFoundSolutions.iterator().next();

                if (prevSol.length() < curSol.length()) {
                    allFoundSolutions.clear();
                    allFoundSolutions.add(curSol);
                } else if (prevSol.length() == curSol.length()) {
                    allFoundSolutions.add(curSol);
                }
            }
        } else {
            char ch = data[idx];

            if (Character.isAlphabetic(ch)) {
                partialSol.append(ch);
                generateRecursively(
                        data, idx + 1, leftCnt, rightCnt, partialSol, allFoundSolutions);
                partialSol.deleteCharAt(partialSol.length() - 1);
            } else if (ch == '(' || ch == ')') {

                int leftOffset = (ch == '(') ? 1 : 0;
                int rightOffset = (ch == ')') ? 1 : 0;

                // use 'ch'
                partialSol.append(ch);
                generateRecursively(
                        data,
                        idx + 1,
                        leftCnt + leftOffset,
                        rightCnt + rightOffset,
                        partialSol,
                        allFoundSolutions);
                partialSol.deleteCharAt(partialSol.length() - 1);

                // skip 'ch'
                generateRecursively(
                        data, idx + 1, leftCnt, rightCnt, partialSol, allFoundSolutions);

            } else {
                throw new IllegalArgumentException("Invalid character detected '%c'".formatted(ch));
            }
        }
    }
}
