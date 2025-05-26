package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.Game24.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

public class Game24Test {

    @Test
    void judgePoint24Case1() {
        assertTrue(judgePoint24(new int[] {4, 1, 8, 7}));
    }

    @Test
    void judgePoint24Case2() {
        assertFalse(judgePoint24(new int[] {1, 2, 1, 2}));
    }

    @Test
    void generateAllPermutationsCase() {
        assertThat(generateAllPermutations(new int[] {4, 1, 8, 7}))
                .hasSize(24)
                .contains(new int[] {8, 4, 7, 1});
    }

    @Test
    void fractionAdd() {
        Fraction x = new Fraction(4, 5);
        Fraction y = new Fraction(5, 2);

        assertEquals(new Fraction(33, 10), x.add(y));
    }

    @Test
    void fractionSub() {
        Fraction x = new Fraction(2, 3);
        Fraction y = new Fraction(4, 5);

        assertEquals(new Fraction(-2, 15), x.sub(y));
    }

    @Test
    void fractionMul() {
        Fraction x = new Fraction(2, 3);
        Fraction y = new Fraction(4, 5);

        assertEquals(new Fraction(8, 15), x.mul(y));
    }

    @Test
    void fractionDiv() {
        Fraction x = new Fraction(2, 3);
        Fraction y = new Fraction(4, 5);

        assertEquals(new Fraction(5, 6), x.div(y));
    }

    @Test
    void infixToPostfixCase() {
        assertEquals("41+87/*", infixToPostfix("(4+1)*(8/7)"));
        assertEquals("41+8-7*", infixToPostfix("((4+1)-8)*7"));
    }

    @Test
    void generateAllValidExpressionsCase() {
        assertThat(generateAllValidExpressions(new int[] {4, 1, 8, 7}))
                .hasSize(64 * 10)
                .contains("(4+1)*(8/7)");
    }

    @Test
    void combineToExpressionCase1() {
        String actualExp =
                combineToExpression(
                        "(4(18))7",
                        new Game24.Operation[] {
                            Game24.Operation.SUB, Game24.Operation.MUL, Game24.Operation.ADD,
                        });

        assertEquals("(4-(1*8))+7", actualExp);
    }

    @Test
    void combineToExpressionCase2() {
        String actualExp =
                combineToExpression(
                        "4187",
                        new Game24.Operation[] {
                            Game24.Operation.DIV, Game24.Operation.ADD, Game24.Operation.ADD,
                        });

        assertEquals("4/1+8+7", actualExp);
    }

    @Test
    void generateAllOperationsCase() {
        assertThat(generateAllOperations())
                .hasSize(64) // we have 3 slots and 4 operations, so total number (4*4*4 = 64)
                .contains(
                        new Game24.Operation[] {
                            Game24.Operation.ADD, Game24.Operation.ADD, Game24.Operation.ADD,
                        })
                .contains(
                        new Game24.Operation[] {
                            Game24.Operation.SUB, Game24.Operation.MUL, Game24.Operation.ADD,
                        });
    }

    @Test
    void generateAllDigitsWithBracketsCase() {

        List<String> expressions = generateAllDigitsWithBrackets(new int[] {4, 1, 8, 7});

        assertThat(expressions)
                .hasSize(10)
                .contains("4187")
                .contains("(41)87")
                .contains("41(87)")
                .contains("(41)(87)")
                .contains("(418)7")
                .contains("((41)8)7")
                .contains("(4(18))7")
                .contains("4(187)")
                .contains("4((18)7)")
                .contains("4(1(87))");
    }
}
