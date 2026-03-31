package com.github.mstepan.leetcode.medium;

import java.util.*;

/**
 * 207. Course Schedule
 *
 * <p>https://leetcode.com/problems/course-schedule/description/
 */
public class CourseSchedule {

    /** Check if graph has cycles or not (check if DAG) */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 1) {
            throw new IllegalArgumentException("'numCourses' must be greater than zero");
        }
        if (prerequisites == null) {
            throw new IllegalArgumentException("'prerequisites' must not be null");
        }
        // short circuit the answer for corner case
        if (numCourses == 1 || prerequisites.length == 0) {
            return true;
        }

        final Graph graph = Graph.fromPrerequisitesAndCourses(numCourses, prerequisites);

        final Map<Integer, Integer> inboundMap = graph.createInboundMap();

        final Queue<Integer> traversalQueue = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : inboundMap.entrySet()) {
            if (entry.getValue() == 0) {
                traversalQueue.add(entry.getKey());
            }
        }

        int processedCount = 0;

        while (!traversalQueue.isEmpty()) {

            int course = traversalQueue.poll();
            ++processedCount;

            for (int adjCourse : graph.adjacentVertexes(course)) {
                int adjInCount =
                        inboundMap.compute(
                                adjCourse,
                                (notUsed, value) -> {
                                    assert value != null;
                                    return value - 1;
                                });

                if (adjInCount == 0) {
                    traversalQueue.add(adjCourse);
                }
            }
        }

        return processedCount == numCourses;
    }

    static class Graph {
        final Map<Integer, List<Integer>> adjList = new HashMap<>();

        static Graph fromPrerequisitesAndCourses(int numCourses, int[][] prerequisites) {
            Graph graph = new Graph();
            for (int i = 0; i < numCourses; ++i) {
                graph.addVertex(i);
            }

            for (int[] dep : prerequisites) {
                graph.addEdge(dep[1], dep[0]);
            }

            return graph;
        }

        private void addVertex(int vertex) {
            adjList.put(vertex, new ArrayList<>());
        }

        private void addEdge(int from, int to) {
            assert adjList.containsKey(from);
            assert adjList.containsKey(to);

            adjList.get(from).add(to);
        }

        public Map<Integer, Integer> createInboundMap() {
            Map<Integer, Integer> inboundMap = new HashMap<>();

            for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
                inboundMap.putIfAbsent(entry.getKey(), 0);

                for (Integer adjVertex : entry.getValue()) {
                    inboundMap.compute(adjVertex, (key, cnt) -> cnt == null ? 1 : cnt + 1);
                }
            }

            return inboundMap;
        }

        public List<Integer> adjacentVertexes(int course) {
            List<Integer> adjacent = adjList.get(course);
            assert adjacent != null;
            return adjacent;
        }
    }
}
