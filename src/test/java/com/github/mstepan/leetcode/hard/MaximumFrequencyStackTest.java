package com.github.mstepan.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumFrequencyStackTest {

    @Test
    void pushAndPop() {
        MaximumFrequencyStack stack = new MaximumFrequencyStack();

        stack.push(5); // The stack is [5]
        stack.push(7); // The stack is [5,7]
        stack.push(5); // The stack is [5,7,5]
        stack.push(7); // The stack is [5,7,5,7]
        stack.push(4); // The stack is [5,7,5,7,4]
        stack.push(5); // The stack is [5,7,5,7,4,5]

        assertEquals(
                5,
                stack.pop()); // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        assertEquals(
                7,
                stack
                        .pop()); // return 7, as 5 and 7 is the most frequent, but 7 is closest to
                                 // the top. The stack becomes [5,7,5,4].
        assertEquals(
                5, stack.pop()); // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        assertEquals(
                4,
                stack
                        .pop()); // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest
                                 // to the top. The stack becomes [5,7].
        assertEquals(7, stack.pop()); // return 7, The stack becomes [5].
        assertEquals(5, stack.pop()); // return57, The stack becomes [].
    }
}
