package com.github.mstepan.leetcode;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {

        TreeNode d = new TreeNode("D");
        TreeNode b = new TreeNode("B");
        b.right = d;

        TreeNode e = new TreeNode("E");
        TreeNode f = new TreeNode("F");
        TreeNode c = new TreeNode("C");
        c.left = e;
        c.right = f;

        TreeNode root = new TreeNode("A");
        root.left = b;
        root.right = c;

        invertInPlace(root);

        System.out.printf("Java version: %s%n", System.getProperty("java.version"));

        System.out.println("Main done...");
    }

    /**
     * Invert binary tree in-place using level order traversal. time: O(N) space: O(N), in worth
     * case may store N/2 references(leaf nodes) in a queue if we have full binary tree
     */
    private static void invertInPlace(TreeNode root) {
        Objects.requireNonNull(root);

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            cur.invertInPlace();

            if (cur.left != null) {
                queue.add(cur.left);
            }

            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        String value;

        TreeNode(String value) {
            this.value = value;
        }

        public void invertInPlace() {
            TreeNode temp = left;
            left = right;
            right = temp;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}
