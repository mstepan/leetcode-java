package com.github.mstepan.leetcode.hard;

/**
 * 2276. Count Integers in Intervals
 *
 * <p>https://leetcode.com/problems/count-integers-in-intervals/description/
 */
public class CountIntegersInIntervals {}

class CountIntervals {

    Node root;
    private int integersCount;

    public CountIntervals() {}

    /**
     * Adds the interval [from, to] to the set of intervals.
     *
     * <p>time: O(log n) on average with AVL balancing
     *
     * @param from the start of the interval (inclusive)
     * @param to the end of the interval (inclusive)
     */
    public void add(int from, int to) {
        if (from > to) {
            throw new IllegalArgumentException(
                    "from must be less than or equal to to: from = " + from + ", to = " + to);
        }

        if (root == null) {
            root = new Node(from, to, null);
            updateIntegersCount(root);
            return;
        }

        root = addNode(new Node(from, to), root);
    }

    private Node addNode(Node nodeToAdd, Node cur) {
        if (cur == null) {
            updateIntegersCount(nodeToAdd);
            return nodeToAdd;
        }

        if (cur.isFullyCovering(nodeToAdd)) {
            return cur;
        }

        if (nodeToAdd.isToTheLeft(cur)) {
            Node nodeToAddAdjusted =
                    new Node(
                            Math.min(nodeToAdd.from, cur.from - 1),
                            Math.min(nodeToAdd.to, cur.from - 1),
                            cur);
            cur.left = addNode(nodeToAddAdjusted, cur.left);
            if (cur.left != null) {
                cur.left.parent = cur;
            }
        }

        if (nodeToAdd.isToTheRight(cur)) {
            Node nodeToAddAdjusted =
                    new Node(
                            Math.max(nodeToAdd.from, cur.to + 1),
                            Math.max(nodeToAdd.to, cur.to + 1),
                            cur);
            cur.right = addNode(nodeToAddAdjusted, cur.right);
            if (cur.right != null) {
                cur.right.parent = cur;
            }
        }

        return balance(cur);
    }

    private Node balance(Node node) {
        if (node == null) {
            return null;
        }

        updateHeight(node);
        int balanceFactor = node.balanceFactor();

        if (balanceFactor > 1) {
            if (node.left != null && node.left.balanceFactor() < 0) {
                node.left = leftRotate(node.left);
                if (node.left != null) {
                    node.left.parent = node;
                }
            }
            return rightRotate(node);
        } else if (balanceFactor < -1) {
            if (node.right != null && node.right.balanceFactor() > 0) {
                node.right = rightRotate(node.right);
                if (node.right != null) {
                    node.right.parent = node;
                }
            }
            return leftRotate(node);
        }

        return node;
    }

    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(node.getHeight(node.left), node.getHeight(node.right)) + 1;
        }
    }

    // Left-Left case
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        if (T2 != null) {
            T2.parent = y;
        }
        x.parent = y.parent;
        y.parent = x;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Right-Right case
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        if (T2 != null) {
            T2.parent = x;
        }
        y.parent = x.parent;
        x.parent = y;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Left-Right case handled by combining rightRotate and leftRotate
    // Right-Left case handled by combining leftRotate and rightRotate

    /**
     * time: O(1)
     *
     * @return the count of integers in all intervals added so far
     */
    public int count() {
        return integersCount;
    }

    private void updateIntegersCount(Node node) {
        if (node != null) {
            integersCount += node.to - node.from + 1;
        }
    }

    private static class Node {
        int from;
        int to;
        Node left;
        Node right;
        Node parent;
        int height;

        Node(int from, int to) {
            this.from = from;
            this.to = to;
            this.height = 1;
        }

        Node(int from, int to, Node parent) {
            this.from = from;
            this.to = to;
            this.parent = parent;
            this.height = 1;
        }

        int getHeight(Node node) {
            return node == null ? 0 : node.height;
        }

        int balanceFactor() {
            int leftHeight = left == null ? 0 : left.height;
            int rightHeight = right == null ? 0 : right.height;
            return leftHeight - rightHeight;
        }

        boolean isFullyCovering(Node other) {
            return from <= other.from && to >= other.to;
        }

        boolean isToTheLeft(Node other) {
            return from < other.from;
        }

        boolean isToTheRight(Node cur) {
            return to > cur.to;
        }
    }
}
