package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidateBinarySearchTreeTest {

    @Test
    void validBST() {
        //   2
        //  / \
        // 1   3
        ValidateBinarySearchTree.TreeNode n1 = new ValidateBinarySearchTree.TreeNode(1);
        ValidateBinarySearchTree.TreeNode n3 = new ValidateBinarySearchTree.TreeNode(3);
        ValidateBinarySearchTree.TreeNode n2 = new ValidateBinarySearchTree.TreeNode(2, n1, n3);

        assertTrue(ValidateBinarySearchTree.isValidBST(n2));
    }

    @Test
    void invalidBST_LeftViolation() {
        //   5
        //  / \
        // 1   4
        //    / \
        //   3   6
        ValidateBinarySearchTree.TreeNode n3 = new ValidateBinarySearchTree.TreeNode(3);
        ValidateBinarySearchTree.TreeNode n6 = new ValidateBinarySearchTree.TreeNode(6);
        ValidateBinarySearchTree.TreeNode n4 = new ValidateBinarySearchTree.TreeNode(4, n3, n6);
        ValidateBinarySearchTree.TreeNode n1 = new ValidateBinarySearchTree.TreeNode(1);
        ValidateBinarySearchTree.TreeNode n5 = new ValidateBinarySearchTree.TreeNode(5, n1, n4);

        assertFalse(ValidateBinarySearchTree.isValidBST(n5));
    }

    @Test
    void invalidBST_RightViolation() {
        //   5
        //  / \
        // 3   7
        //      /
        //     4   <-- should be > 5, but isn't
        ValidateBinarySearchTree.TreeNode n4 = new ValidateBinarySearchTree.TreeNode(4);
        ValidateBinarySearchTree.TreeNode n7 = new ValidateBinarySearchTree.TreeNode(7, n4, null);
        ValidateBinarySearchTree.TreeNode n3 = new ValidateBinarySearchTree.TreeNode(3);
        ValidateBinarySearchTree.TreeNode n5 = new ValidateBinarySearchTree.TreeNode(5, n3, n7);

        assertFalse(ValidateBinarySearchTree.isValidBST(n5));
    }

    @Test
    void singleNode() {
        ValidateBinarySearchTree.TreeNode n = new ValidateBinarySearchTree.TreeNode(42);
        assertTrue(ValidateBinarySearchTree.isValidBST(n));
    }

    @Test
    void validBST_MinAndMaxValues() {
        // Long.MIN_VALUE < 0 < Long.MAX_VALUE (as node values are ints, but method uses long
        // boundaries)
        ValidateBinarySearchTree.TreeNode left =
                new ValidateBinarySearchTree.TreeNode(Integer.MIN_VALUE);
        ValidateBinarySearchTree.TreeNode right =
                new ValidateBinarySearchTree.TreeNode(Integer.MAX_VALUE);
        ValidateBinarySearchTree.TreeNode root =
                new ValidateBinarySearchTree.TreeNode(0, left, right);

        assertTrue(ValidateBinarySearchTree.isValidBST(root));
    }

    @Test
    void invalidBST_SubtreeViolation() {
        //   10
        //  /  \
        // 5   15
        //    /  \
        //   6    20
        ValidateBinarySearchTree.TreeNode n6 = new ValidateBinarySearchTree.TreeNode(6);
        ValidateBinarySearchTree.TreeNode n20 = new ValidateBinarySearchTree.TreeNode(20);
        ValidateBinarySearchTree.TreeNode n15 = new ValidateBinarySearchTree.TreeNode(15, n6, n20);
        ValidateBinarySearchTree.TreeNode n5 = new ValidateBinarySearchTree.TreeNode(5);
        ValidateBinarySearchTree.TreeNode n10 = new ValidateBinarySearchTree.TreeNode(10, n5, n15);

        assertFalse(ValidateBinarySearchTree.isValidBST(n10));
    }
}
