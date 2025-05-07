package com.github.mstepan.leetcode.hard;

import static com.github.mstepan.leetcode.hard.RemoveInvalidParentheses.removeInvalidParentheses;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class RemoveInvalidParenthesesTest {

    @Test
    void removeInvalidParenthesesNormalCase1() {

        List<String> results = removeInvalidParentheses("()())()");

        assertThat(results)
                .hasSize(2)
                .containsExactlyInAnyOrder("(())()", "()()()"); // ["(())()","()()()"]
    }

    @Test
    void removeInvalidParenthesesNormalCase2() {

        List<String> results = removeInvalidParentheses("(a)())()");

        assertThat(results)
                .hasSize(2)
                .containsExactlyInAnyOrder("(a())()", "(a)()()"); // ["(a())()","(a)()()"]
    }

    @Test
    void removeInvalidParenthesesNormalCase3() {

        List<String> results = removeInvalidParentheses(")(");

        assertThat(results).hasSize(1).containsExactly(""); // []
    }

    @Test
    void removeInvalidParenthesesNormalCase4() {

        List<String> results = removeInvalidParentheses(")(f");

        assertThat(results).hasSize(1).containsExactly("f"); // ["f"]
    }
}
