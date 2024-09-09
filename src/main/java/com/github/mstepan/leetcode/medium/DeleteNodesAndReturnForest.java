package com.github.mstepan.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 1110. Delete Nodes And Return Forest
 *
 * <p>https://leetcode.com/problems/delete-nodes-and-return-forest/description/
 */
public class DeleteNodesAndReturnForest {

    /**
     * time: O(N)
     *
     * <p>space: O(N)
     */
    public static List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        Objects.requireNonNull(root, "null 'root' detected");
        Objects.requireNonNull(root, "null 'toDelete' detected");

        Set<Integer> nodesToDelete = Arrays.stream(toDelete).boxed().collect(Collectors.toSet());

        Deque<TreeNode> inProgress = new ArrayDeque<>();
        inProgress.add(root);

        List<TreeNode> result = new ArrayList<>();

        while (!inProgress.isEmpty()) {
            TreeNode cur = inProgress.poll();

            if (nodesToDelete.contains(cur.val)) {
                if (cur.left != null) {
                    inProgress.add(cur.left);
                }
                if (cur.right != null) {
                    inProgress.add(cur.right);
                }
            } else {
                result.add(cur);
                checkSubtreesForDeletion(cur, inProgress, nodesToDelete);
            }
        }

        return result;
    }

    private static void checkSubtreesForDeletion(
            TreeNode cur, Deque<TreeNode> inProgress, Set<Integer> nodesToDelete) {

        assert !nodesToDelete.contains(cur.val);

        checkIfNodeShouldBeDeleted(cur, cur.left, Direction.Left, inProgress, nodesToDelete);
        checkIfNodeShouldBeDeleted(cur, cur.right, Direction.Right, inProgress, nodesToDelete);
    }

    enum Direction {
        Left,
        Right
    }

    private static void checkIfNodeShouldBeDeleted(
            TreeNode parent,
            TreeNode child,
            Direction direction,
            Deque<TreeNode> inProgress,
            Set<Integer> nodesToDelete) {

        if (child != null) {

            if (nodesToDelete.contains(child.val)) {
                if (child.left != null) {
                    inProgress.add(child.left);
                }
                if (child.right != null) {
                    inProgress.add(child.right);
                }

                if (direction == Direction.Left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                child.left = null;
                child.right = null;
            } else {
                checkSubtreesForDeletion(child, inProgress, nodesToDelete);
            }
        }
    }

    // =================================================================================================================
    // don't copy below code
    // =================================================================================================================
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
