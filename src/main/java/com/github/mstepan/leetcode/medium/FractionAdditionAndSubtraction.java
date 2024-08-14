package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 592. Fraction Addition and Subtraction
 *
 * <p>https://leetcode.com/problems/fraction-addition-and-subtraction/
 */
public class FractionAdditionAndSubtraction {

    public String fractionAddition(String expression) {
        Objects.requireNonNull(expression);

        int index = 0;

        FactionAndIndex leftSideRes = readFraction(expression, index);

        Fraction left = leftSideRes.fraction;
        index = leftSideRes.index;

        while (index < expression.length()) {
            OperationAndIndex operationRes = readOperation(expression, index);
            index = operationRes.index;

            FactionAndIndex rightSideRes = readFraction(expression, index);

            Fraction right = rightSideRes.fraction;
            index = rightSideRes.index;

            if (operationRes.operation == Operation.ADD) {
                left = left.add(right);
            } else {
                left = left.sub(right);
            }
        }

        return left.toString();
    }

    private static FactionAndIndex readFraction(String expression, int offset) {
        assert offset < expression.length();

        int idx = offset;

        int sign = 1;

        if (expression.charAt(idx) == '-') {
            sign = -1;
            ++idx;
        }

        int num = 0;

        while (idx < expression.length() && expression.charAt(idx) != '/') {
            num = num * 10 + (expression.charAt(idx) - '0');
            ++idx;
        }

        idx += 1;

        int denom = 0;

        while (idx < expression.length() && Character.isDigit(expression.charAt(idx))) {
            denom = denom * 10 + (expression.charAt(idx) - '0');
            ++idx;
        }

        return new FactionAndIndex(new Fraction(sign * num, denom), idx);
    }

    private static OperationAndIndex readOperation(String expression, int offset) {
        assert offset < expression.length();

        int idx = offset;

        Operation op = (expression.charAt(idx) == '+') ? Operation.ADD : Operation.SUB;
        ++idx;

        return new OperationAndIndex(op, idx);
    }

    private static final class FactionAndIndex {
        final Fraction fraction;
        final int index;

        public FactionAndIndex(Fraction fraction, int index) {
            this.fraction = fraction;
            this.index = index;
        }
    }

    private static final class Fraction {
        final int num;

        final int denom;

        public Fraction(int num, int denom) {
            int div = gcd(num, denom);
            this.num = (num / div);
            this.denom = (denom / div);
        }

        @Override
        public String toString() {
            return num + "/" + denom;
        }

        public Fraction add(Fraction right) {
            int newNum = (num * right.denom) + (right.num * denom);
            int newDenom = denom * right.denom;
            return new Fraction(newNum, newDenom);
        }

        public Fraction sub(Fraction right) {
            int newNum = (num * right.denom) - (right.num * denom);
            int newDenom = denom * right.denom;
            return new Fraction(newNum, newDenom);
        }
    }

    private static int gcd(int a, int b) {

        int first = Math.absExact(a);
        int second = Math.absExact(b);

        while (second != 0) {
            int rem = first % second;

            first = second;
            second = rem;
        }

        return first;
    }

    private static final class OperationAndIndex {
        final Operation operation;
        final int index;

        public OperationAndIndex(Operation operation, int index) {
            this.operation = operation;
            this.index = index;
        }
    }

    private enum Operation {
        ADD,
        SUB
    }
}
