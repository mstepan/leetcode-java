package com.github.mstepan.leetcode.medium;

import java.util.Objects;

/**
 * 606. Construct String from Binary Tree
 * https://leetcode.com/problems/construct-string-from-binary-tree/description/
 */
public class ConstructStringFromBinaryTree {

    public String tree2str(TreeNode root) {
        Objects.requireNonNull(root, "null 'root' node detected");
        return buildRec(root);
    }

    private static String buildRec(TreeNode cur) {
        assert cur != null;

        // cur is LEAF
        if (cur.left == null && cur.right == null) {
            return String.valueOf(cur.val);
        }
        StringBuilder repr = new StringBuilder();

        repr.append(cur.val);

        if (cur.left == null) {
            repr.append("()");
        } else {
            repr.append("(").append(buildRec(cur.left)).append(")");
        }

        if (cur.right != null) {
            repr.append("(").append(buildRec(cur.right)).append(")");
        }

        return repr.toString();
    }

    // === Don't copy below code ===

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
