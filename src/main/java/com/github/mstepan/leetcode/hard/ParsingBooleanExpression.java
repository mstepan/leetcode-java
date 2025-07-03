package com.github.mstepan.leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 1106. Parsing A Boolean Expression
 *
 * <p>https://leetcode.com/problems/parsing-a-boolean-expression/
 */
public class ParsingBooleanExpression {

    /** Uses recursive-descent parser to create an Abstract Syntax Tree and evaluate root node. */
    public static boolean parseBoolExpr(String expression) {
        Objects.requireNonNull(expression);

        Context ctx = new Context(expression);
        ExpNode rootNode = parse(ctx);

        return rootNode.eval();
    }

    @SuppressWarnings("all")
    private static ExpNode parse(Context ctx) {

        Token token = ctx.next();

        if (token == Token.TRUE) {
            return new TrueNode();
        }

        if (token == Token.FALSE) {
            return new FalseNode();
        }

        Token nextToken = ctx.next();
        assert nextToken == Token.LEFT_PAREN;

        List<ExpNode> subExpressions = new ArrayList<>();

        while (true) {
            ExpNode subExpression = parse(ctx);
            subExpressions.add(subExpression);

            Token nextTokenAfterSubExp = ctx.next();

            if (nextTokenAfterSubExp == Token.RIGHT_PAREN) {
                break;
            }

            assert nextTokenAfterSubExp == Token.COMMA;
        }

        switch (token) {
            case AND:
                {
                    return new AndNode(subExpressions);
                }
            case OR:
                {
                    return new OrNode(subExpressions);
                }
            case NOT:
                {
                    assert subExpressions.size() == 1;
                    return new NotNode(subExpressions.get(0));
                }
        }

        throw new IllegalStateException("Can't be here");
    }

    enum Token {
        TRUE,
        FALSE,
        OR,
        AND,
        NOT,
        LEFT_PAREN,
        RIGHT_PAREN,
        COMMA,
    }

    private static final class Context {
        final String originalExpression;
        final char[] expression;
        int idx;

        Context(String expression) {
            assert expression != null;
            this.originalExpression = expression;
            this.expression = expression.toCharArray();
        }

        public Token next() {
            if (idx >= expression.length) {
                throw new IllegalStateException(
                        "Not more tokens left in expression '" + originalExpression + "'");
            }
            char ch = expression[idx];
            ++idx;

            switch (ch) {
                case 't':
                    {
                        return Token.TRUE;
                    }
                case 'f':
                    {
                        return Token.FALSE;
                    }
                case '&':
                    {
                        return Token.AND;
                    }
                case '|':
                    {
                        return Token.OR;
                    }
                case '!':
                    {
                        return Token.NOT;
                    }
                case '(':
                    {
                        return Token.LEFT_PAREN;
                    }
                case ')':
                    {
                        return Token.RIGHT_PAREN;
                    }
                case ',':
                    {
                        return Token.COMMA;
                    }
                default:
                    {
                        throw new IllegalStateException("Undefined token '" + ch + "'");
                    }
            }
        }
    }

    interface ExpNode {
        boolean eval();
    }

    record TrueNode() implements ExpNode {
        @Override
        public boolean eval() {
            return true;
        }
    }

    record FalseNode() implements ExpNode {
        @Override
        public boolean eval() {
            return false;
        }
    }

    record AndNode(List<ExpNode> subExpressions) implements ExpNode {

        @Override
        public boolean eval() {

            for (ExpNode singleSub : subExpressions) {
                if (!singleSub.eval()) {
                    return false;
                }
            }

            return true;
        }
    }

    record OrNode(List<ExpNode> subExpressions) implements ExpNode {

        @Override
        public boolean eval() {

            for (ExpNode singleSub : subExpressions) {
                if (singleSub.eval()) {
                    return true;
                }
            }

            return false;
        }
    }

    record NotNode(ExpNode subExp) implements ExpNode {

        @Override
        public boolean eval() {
            return !subExp.eval();
        }
    }
}
