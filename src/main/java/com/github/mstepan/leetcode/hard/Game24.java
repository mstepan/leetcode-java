package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 679. 24 Game
 *
 * <p>https://leetcode.com/problems/24-game/description/
 */
public class Game24 {

    public static boolean judgePoint24(int[] cards) {

        for (int[] singleCard : generateAllPermutations(cards)) {

            for (String singleExp : generateAllValidExpressions(singleCard)) {

                String postfixExp = infixToPostfix(singleExp);
                Fraction result = evaluateOnStack(postfixExp);

                if (result.equals(Fraction.TWENTY_FOUR)) {
                    return true;
                }
            }
        }

        return false;
    }

    static List<int[]> generateAllPermutations(int[] cards) {
        List<int[]> permutations = new ArrayList<>();

        generateRec(cards, 0, new int[cards.length], 0, permutations);

        return permutations;
    }

    private static void generateRec(
            int[] cards, int usedMask, int[] partialRes, int idx, List<int[]> permutations) {
        if (idx >= partialRes.length) {
            permutations.add(Arrays.copyOf(partialRes, partialRes.length));
            return;
        }

        for (int i = 0; i < cards.length; i++) {
            if ((usedMask & (1 << i)) == 0) {
                partialRes[idx] = cards[i];
                generateRec(cards, usedMask | (1 << i), partialRes, idx + 1, permutations);
                partialRes[idx] = 0;
            }
        }
    }

    static String infixToPostfix(String exp) {
        StringBuilder res = new StringBuilder();
        Deque<Character> opsStack = new ArrayDeque<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (ch == '(') {
                opsStack.push(ch);
            } else if (ch == ')') {
                while (true) {
                    if (opsStack.isEmpty()) {
                        throw new IllegalStateException("Incorrect expression: %s".formatted(exp));
                    }

                    char other = opsStack.pop();

                    if (other == '(') {
                        break;
                    }

                    res.append(other);
                }

            } else if (Character.isDigit(ch)) {
                res.append(ch);
            } else {
                // operator found

                while (!opsStack.isEmpty()) {
                    char prevOp = opsStack.pop();

                    if (prevOp == '(' || cmpPrecedence(ch, prevOp) > 0) {
                        opsStack.push(prevOp);
                        break;
                    }

                    res.append(prevOp);
                }

                opsStack.push(ch);
            }
        }

        while (!opsStack.isEmpty()) {
            res.append(opsStack.pop());
        }

        return res.toString();
    }

    private static int cmpPrecedence(char ch, char prevOp) {
        return Integer.compare(opPrecedence(ch), opPrecedence(prevOp));
    }

    private static int opPrecedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        }

        return 2;
    }

    private static Fraction evaluateOnStack(String postfixExp) {

        Deque<Fraction> evalStack = new ArrayDeque<>();

        for (int i = 0; i < postfixExp.length(); i++) {
            char ch = postfixExp.charAt(i);

            if (Character.isDigit(ch)) {
                int digit = ch - '0';
                evalStack.push(Fraction.fromNum(digit));
            } else {
                // ch is operator +, -, *, /
                assert ch == '+' || ch == '-' || ch == '*' || ch == '/';
                assert evalStack.size() >= 2;

                Fraction second = evalStack.pop();
                Fraction first = evalStack.pop();
                Fraction res = calculate(ch, first, second);

                evalStack.push(res);
            }
        }

        if (evalStack.size() != 1) {
            throw new IllegalStateException("Can't evaluate expression: %s".formatted(postfixExp));
        }

        return evalStack.pop();
    }

    private static Fraction calculate(char ch, Fraction first, Fraction second) {

        return switch (ch) {
            case '+' -> first.add(second);
            case '-' -> first.sub(second);
            case '*' -> first.mul(second);
            case '/' -> first.div(second);
            default -> throw new IllegalStateException("Incorrect operation detected: " + ch);
        };
    }

    static List<String> generateAllValidExpressions(int[] cards) {
        List<String> digitsAndBrackets = generateAllDigitsWithBrackets(cards);
        List<Operation[]> operations = generateAllOperations();

        List<String> allExpressions = new ArrayList<>();

        for (String singleDigitAndBracket : digitsAndBrackets) {
            for (Operation[] singleOps : operations) {
                String exp = combineToExpression(singleDigitAndBracket, singleOps);
                allExpressions.add(exp);
            }
        }

        return allExpressions;
    }

    static String combineToExpression(String singleDigitAndBracket, Operation[] singleOps) {

        StringBuilder finalExp = new StringBuilder();

        int idx = 0;

        while (!Character.isDigit(singleDigitAndBracket.charAt(idx))) {
            finalExp.append(singleDigitAndBracket.charAt(idx));
            ++idx;
        }

        // (4(18))7

        for (Operation singleOp : singleOps) {
            finalExp.append(singleDigitAndBracket.charAt(idx));
            ++idx;

            while (singleDigitAndBracket.charAt(idx) == ')') {
                finalExp.append(singleDigitAndBracket.charAt(idx));
                ++idx;
            }

            finalExp.append(singleOp.strRepresentation());

            while (singleDigitAndBracket.charAt(idx) == '(') {
                finalExp.append(singleDigitAndBracket.charAt(idx));
                ++idx;
            }
        }

        while (idx < singleDigitAndBracket.length()) {
            finalExp.append(singleDigitAndBracket.charAt(idx));
            ++idx;
        }

        return finalExp.toString();
    }

    static List<Operation[]> generateAllOperations() {
        List<Operation[]> operations = new ArrayList<>();

        generateOpsRec(new Operation[3], 0, operations);

        return operations;
    }

    private static void generateOpsRec(Operation[] curSol, int idx, List<Operation[]> result) {
        if (idx >= curSol.length) {
            result.add(Arrays.copyOf(curSol, curSol.length));
            return;
        }

        for (Operation singleOp : Operation.values()) {
            curSol[idx] = singleOp;
            generateOpsRec(curSol, idx + 1, result);
        }
    }

    static List<String> generateAllDigitsWithBrackets(int[] cards) {

        List<String> expressions = new ArrayList<>();

        // "4187"
        expressions.add(insertBrackets(cards));

        // "(41)87"
        expressions.add(insertBrackets(cards, new Range(0, 1)));

        // "41(87)"
        expressions.add(insertBrackets(cards, new Range(2, 3)));

        // (41)(87)
        expressions.add(insertBrackets(cards, new Range(0, 1), new Range(2, 3)));

        // "(418)7"
        expressions.add(insertBrackets(cards, new Range(0, 2)));

        // ((41)8)7
        expressions.add(insertBrackets(cards, new Range(0, 1), new Range(0, 2)));

        // "(4(18))7"
        expressions.add(insertBrackets(cards, new Range(0, 2), new Range(1, 2)));

        // "4(187)"
        expressions.add(insertBrackets(cards, new Range(1, 3)));

        // "4((18)7)"
        expressions.add(insertBrackets(cards, new Range(1, 3), new Range(1, 2)));

        // "4(1(87))"
        expressions.add(insertBrackets(cards, new Range(1, 3), new Range(2, 3)));

        return expressions;
    }

    private static String insertBrackets(int[] cards, Range... ranges) {

        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < cards.length; i++) {

            for (Range singleRange : ranges) {
                if (singleRange.beforeIdx == i) {
                    buf.append("(");
                }
            }

            buf.append(cards[i]);

            for (Range singleRange : ranges) {
                if (singleRange.afterIdx == i) {
                    buf.append(")");
                }
            }
        }

        return buf.toString();
    }

    record Range(int beforeIdx, int afterIdx) {}

    enum Operation {
        ADD {
            @Override
            String strRepresentation() {
                return "+";
            }
        },
        SUB {
            @Override
            String strRepresentation() {
                return "-";
            }
        },
        MUL {
            @Override
            String strRepresentation() {
                return "*";
            }
        },
        DIV {
            @Override
            String strRepresentation() {
                return "/";
            }
        };

        abstract String strRepresentation();
    }

    record Fraction(int num, int den) {
        static final Fraction TWENTY_FOUR = new Fraction(24, 1);
        private static final Fraction ZERO = new Fraction(0, 0);

        public Fraction {
            int gcd = gcd(num, den);

            if (gcd > 1) {
                num = num / gcd;
                den = den / gcd;
            }
        }

        static int gcd(int first, int second) {
            int x = Math.abs(first);
            int y = Math.abs(second);

            while (y != 0) {
                int rem = x % y;
                x = y;
                y = rem;
            }

            return x;
        }

        public static Fraction fromNum(int digit) {
            return new Fraction(digit, 1);
        }

        public Fraction add(Fraction other) {
            int numRes = this.num * other.den + other.num * this.den;
            int denRes = this.den * other.den;

            return new Fraction(numRes, denRes);
        }

        public Fraction sub(Fraction other) {
            int numRes = this.num * other.den - other.num * this.den;
            int denRes = this.den * other.den;

            return new Fraction(numRes, denRes);
        }

        public Fraction mul(Fraction other) {

            if (this.num == 0) {
                return Fraction.ZERO;
            }
            return new Fraction(this.num * other.num, this.den * other.den);
        }

        public Fraction div(Fraction other) {
            return new Fraction(this.num * other.den, this.den * other.num);
        }
    }
}
