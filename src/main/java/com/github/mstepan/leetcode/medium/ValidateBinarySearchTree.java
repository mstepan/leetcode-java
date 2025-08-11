package com.github.mstepan.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * 98. Validate Binary Search Tree
 *
 * <p>https://leetcode.com/problems/validate-binary-search-tree/description
 */
public class ValidateBinarySearchTree {

    public static boolean isValidBST(TreeNode root) {
        Objects.requireNonNull(root);

        Deque<PartialRes> queue = new ArrayDeque<>();
        queue.add(new PartialRes(root, Long.MIN_VALUE, Long.MAX_VALUE));

        while (!queue.isEmpty()) {
            PartialRes curRes = queue.poll();
            TreeNode cur = curRes.node;

            if (cur.val <= curRes.leftBoundary || cur.val >= curRes.rightBoundary) {
                return false;
            }

            if (cur.left != null) {
                if (cur.left.val >= cur.val) {
                    return false;
                }

                queue.add(new PartialRes(cur.left, curRes.leftBoundary, cur.val));
            }

            if (cur.right != null) {
                if (cur.right.val <= cur.val) {
                    return false;
                }

                queue.add(new PartialRes(cur.right, cur.val, curRes.rightBoundary));
            }
        }

        return true;
    }

    record PartialRes(TreeNode node, long leftBoundary, long rightBoundary) {}

    //
    // DON't COPY below code, will be provided by leetcode
    //
    public static class TreeNode {
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
