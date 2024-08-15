package com.github.mstepan.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConstructStringFromBinaryTreeTest {

    @Test
    void tree2str() {

        ConstructStringFromBinaryTree.TreeNode four = new ConstructStringFromBinaryTree.TreeNode(4);
        ConstructStringFromBinaryTree.TreeNode two =
                new ConstructStringFromBinaryTree.TreeNode(2, four, null);

        ConstructStringFromBinaryTree.TreeNode three =
                new ConstructStringFromBinaryTree.TreeNode(3);

        ConstructStringFromBinaryTree.TreeNode one =
                new ConstructStringFromBinaryTree.TreeNode(1, two, three);

        assertEquals("1(2(4))(3)", new ConstructStringFromBinaryTree().tree2str(one));
    }
}
