package com.github.mstepan.leetcode.hard;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 460. LFU Cache
 *
 * <p>https://leetcode.com/problems/lfu-cache/description/
 */
public class LFUCache {

    private final Map<Integer, NodeValue> valuesByKey = new HashMap<>();
    private final Map<Integer, LinkedHashMap<Integer, NodeValue>> valuesByFrequency =
            new HashMap<>();

    private final int capacity;
    private int size;
    private int minFreq;

    public LFUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
    }

    public int get(int key) {
        NodeValue node = valuesByKey.get(key);

        if (node == null) {
            return -1;
        }

        promote(node, node.value());
        return node.value();
    }

    public void put(int key, int value) {
        NodeValue node = valuesByKey.get(key);
        if (node != null) {
            promote(node, value);
            return;
        }

        if (size == capacity) {
            evictLeastFrequentlyUsed();
        }

        NodeValue newNode = new NodeValue(key, value, 1);
        valuesByKey.put(key, newNode);
        valuesByFrequency.computeIfAbsent(1, ignored -> new LinkedHashMap<>()).put(key, newNode);
        size++;
        minFreq = 1;
    }

    private void promote(NodeValue node, int value) {
        Map<Integer, NodeValue> currentFrequencyValues = valuesByFrequency.get(node.freq());
        currentFrequencyValues.remove(node.key());

        if (currentFrequencyValues.isEmpty()) {
            valuesByFrequency.remove(node.freq());
            if (minFreq == node.freq()) {
                minFreq++;
            }
        }

        NodeValue promotedNode = new NodeValue(node.key(), value, node.freq() + 1);
        valuesByKey.put(node.key(), promotedNode);
        valuesByFrequency
                .computeIfAbsent(promotedNode.freq(), ignored -> new LinkedHashMap<>())
                .put(promotedNode.key(), promotedNode);
    }

    private void evictLeastFrequentlyUsed() {
        Map<Integer, NodeValue> leastFrequentValues = valuesByFrequency.get(minFreq);
        Map.Entry<Integer, NodeValue> leastRecentlyUsed =
                leastFrequentValues.entrySet().iterator().next();

        leastFrequentValues.remove(leastRecentlyUsed.getKey());
        if (leastFrequentValues.isEmpty()) {
            valuesByFrequency.remove(minFreq);
        }

        valuesByKey.remove(leastRecentlyUsed.getKey());
        size--;
    }

    private record NodeValue(int key, int value, int freq) {}
}
