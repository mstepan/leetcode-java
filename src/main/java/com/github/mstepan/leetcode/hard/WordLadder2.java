package com.github.mstepan.leetcode.hard;

import java.util.*;

/**
 * 126. Word Ladder II
 *
 * <p>https://leetcode.com/problems/word-ladder-ii/description/
 */
public class WordLadder2 {

    /**
     * time: O(N^2)
     *
     * <p>space: O(N^2)
     */
    public static List<List<String>> findLadders(
            String beginWord, String endWord, List<String> wordList) {
        Objects.requireNonNull(beginWord);
        Objects.requireNonNull(endWord);
        Objects.requireNonNull(wordList);

        List<List<String>> results = new ArrayList<>();

        List<String> seeds = findSimilar(beginWord, wordList);

        Graph g = new Graph(wordList);

        Queue<WordsPath> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        for (String singleSeed : seeds) {

            //TODO: merge paths with similar node

            if (singleSeed.equals(beginWord)) {
                queue.add(new WordsPath(singleSeed));
            } else {
                if (singleSeed.equals(endWord)) {
                    results.add(List.of(beginWord, singleSeed));
                }
                queue.add(new WordsPath(beginWord, singleSeed));
            }

            visited.add(singleSeed);
        }

        if (!results.isEmpty()) {
            return results;
        }

        while (!queue.isEmpty()) {
            Queue<WordsPath> next = new ArrayDeque<>();
            Set<String> visitedNext = new HashSet<>();

            while (!queue.isEmpty()) {
                WordsPath curPath = queue.poll();

                //TODO: merge paths with similar node


                for (String singleNeighbour : g.findNeighbours(curPath.last())) {

                    if (singleNeighbour.equals(endWord)) {
                        results.add(new WordsPath(curPath, singleNeighbour).buildPath());
                    } else if (!visited.contains(singleNeighbour)) {
                        next.add(new WordsPath(curPath, singleNeighbour));
                        visitedNext.add(singleNeighbour);
                    }
                }
            }

            queue = next;
            visited.addAll(visitedNext);
        }

        return filterBiggerLength(results);
    }

    private static List<List<String>> filterBiggerLength(List<List<String>> results) {
        List<List<String>> filtered = new ArrayList<>();
        int minLengthSoFar = Integer.MAX_VALUE;

        for (List<String> singleRes : results) {
            if (singleRes.size() < minLengthSoFar) {
                filtered.clear();
                minLengthSoFar = singleRes.size();
                filtered.add(singleRes);
            } else if (singleRes.size() == minLengthSoFar) {
                filtered.add(singleRes);
            }
        }

        return filtered;
    }

    private static List<String> findSimilar(String beginWord, List<String> wordList) {

        List<String> result = new ArrayList<>();
        for (String word : wordList) {
            if (distance(beginWord, word) <= 1) {
                result.add(word);
            }
        }

        return result;
    }

    private static int distance(String v1, String v2) {
        assert v1.length() == v2.length();

        int diffCnt = 0;

        for (int i = 0; i < v1.length(); ++i) {
            if (v1.charAt(i) != v2.charAt(i)) {
                ++diffCnt;
            }
        }

        return diffCnt;
    }

    /** Undirected graph. */
    private static final class Graph {

        private final Map<String, Set<String>> adjList = new HashMap<>();

        Graph(List<String> wordList) {
            for (int i = 0; i < wordList.size() - 1; ++i) {
                for (int j = i + 1; j < wordList.size(); ++j) {
                    String srcVer = wordList.get(i);
                    String destVer = wordList.get(j);

                    if (distance(srcVer, destVer) < 2) {
                        addEdge(srcVer, destVer);
                    }
                }
            }
        }

        private void addEdge(String v1, String v2) {
            adjListForVertex(v1).add(v2);
            adjListForVertex(v2).add(v1);
        }

        private Set<String> adjListForVertex(String vertex) {
            if (!adjList.containsKey(vertex)) {
                adjList.put(vertex, new HashSet<>());
            }

            return adjList.get(vertex);
        }

        public Set<String> findNeighbours(String ver) {
            return adjListForVertex(ver);
        }
    }

    private static final class WordsPath {
        private final List<String> path = new ArrayList<>();

        WordsPath(WordsPath prefix, String last) {
            path.addAll(prefix.path);
            path.add(last);
        }

        WordsPath(String... words) {
            path.addAll(Arrays.asList(words));
        }

        public String last() {
            return path.get(path.size() - 1);
        }

        public List<String> buildPath() {
            return path;
        }
    }
}
