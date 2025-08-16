package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.CloneGraph.cloneGraph;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Test;

public class CloneGraphTest {

    @Test
    void case1() {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);

        one.neighbors.add(two);
        one.neighbors.add(four);

        two.neighbors.add(one);
        two.neighbors.add(three);

        three.neighbors.add(two);
        three.neighbors.add(four);

        four.neighbors.add(one);
        four.neighbors.add(three);

        Node oneCopy = cloneGraph(one);

        assertEquals(one.val, oneCopy.val);
        assertNotSame(one, oneCopy);
    }
}
