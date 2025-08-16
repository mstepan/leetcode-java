package com.github.mstepan.leetcode.medium;

import java.util.*;

/**
 * 133. Clone Graph
 *
 * <p>https://leetcode.com/problems/clone-graph/description/
 */
public class CloneGraph {

    /**
     * Use BFS and clone operation with separate tracking of copied Nodes.
     *
     * <p>time: O(V+E)
     *
     * <p>space: O(V)
     */
    public static Node cloneGraph(Node start) {

        if (start == null) {
            return null;
        }

        Node startCopy = new Node(start.val);

        Map<Integer, Node> copiedNodes = new HashMap<>();
        copiedNodes.put(start.val, startCopy);

        Deque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(start, startCopy));

        while (!queue.isEmpty()) {

            Pair pair = queue.poll();

            Node origin = pair.origin;
            Node copy = pair.copy;

            for (Node realAdj : origin.neighbors) {
                Node adjCopy = copiedNodes.get(realAdj.val);

                if (adjCopy == null) {
                    adjCopy = new Node(realAdj.val);
                    copiedNodes.put(realAdj.val, adjCopy);
                    queue.add(new Pair(realAdj, adjCopy));
                }

                link(copy, adjCopy);
            }
        }

        return startCopy;
    }

    private static void link(Node copy, Node adjCopy) {

        if (!copy.neighbors.contains(adjCopy)) {
            copy.neighbors.add(adjCopy);
        }

        if (!adjCopy.neighbors.contains(copy)) {
            adjCopy.neighbors.add(copy);
        }
    }

    record Pair(Node origin, Node copy) {}
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
