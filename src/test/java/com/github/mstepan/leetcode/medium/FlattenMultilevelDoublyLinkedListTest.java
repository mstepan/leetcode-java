package com.github.mstepan.leetcode.medium;

import static com.github.mstepan.leetcode.medium.FlattenMultilevelDoublyLinkedList.Node;
import static com.github.mstepan.leetcode.medium.FlattenMultilevelDoublyLinkedList.flatten;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class FlattenMultilevelDoublyLinkedListTest {

    @Test
    void nullHead() {
        assertThat(flatten(null)).isNull();
    }

    @Test
    void singleNode() {
        Node one = new Node(1);

        Node flatList = flatten(one);

        assertThat(flatList).isSameAs(one);
    }

    @Test
    void fewNodes() {
        Node one = new Node(1);

        Node two = new Node(2);
        one.addAfter(two);

        Node three = new Node(3);
        two.addAfter(three);

        Node flatList = flatten(one);

        assertThat(flatList).isSameAs(one);
        assertThat(flatList.next).isSameAs(two);
        assertThat(flatList.next.next).isSameAs(three);
    }

    @Test
    void case2() {
        Node one = new Node(1);

        Node two = new Node(2);
        one.addAfter(two);

        Node three = new Node(3);
        one.child = three;

        Node flatList = flatten(one);

        assertThat(flatList).isSameAs(one);
        assertThat(flatList.next).isSameAs(three);
        assertThat(flatList.next.next).isSameAs(two);
    }

    @Test
    void deepNestedSingleBranch() {
        Node one = new Node(1);

        Node two = new Node(2);
        one.child = two;

        Node three = new Node(3);
        two.child = three;

        Node flatList = flatten(one);

        assertFlattened(flatList, 1, 2, 3);
    }

    @Test
    void childInsertedBeforeNextWithNestedLevels() {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        one.addAfter(two);
        two.addAfter(three);

        Node seven = new Node(7);
        Node eight = new Node(8);
        two.child = seven;
        seven.addAfter(eight);

        Node eleven = new Node(11);
        eight.child = eleven;

        Node flatList = flatten(one);

        assertFlattened(flatList, 1, 2, 7, 8, 11, 3);
    }

    @Test
    void multipleChildrenAtDifferentNodes() {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        one.addAfter(two);
        two.addAfter(three);

        Node four = new Node(4);
        one.child = four;

        Node five = new Node(5);
        Node six = new Node(6);
        three.child = five;
        five.addAfter(six);

        Node flatList = flatten(one);

        assertFlattened(flatList, 1, 4, 2, 3, 5, 6);
    }

    private static void assertFlattened(Node head, int... expectedOrder) {
        Node cur = head;
        Node prev = null;

        for (int expectedVal : expectedOrder) {
            assertThat(cur).as("node for value %s", expectedVal).isNotNull();
            assertThat(cur.val).isEqualTo(expectedVal);
            assertThat(cur.prev).isSameAs(prev);
            assertThat(cur.child).isNull();

            prev = cur;
            cur = cur.next;
        }

        assertThat(cur).isNull();
        assertThat(head == null ? null : head.prev).isNull();
    }
}
