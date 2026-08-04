package com.github.mstepan.leetcode.hard;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 460. LFU Cache
 *
 * <p>https://leetcode.com/problems/lfu-cache/description/
 *
 * <p>This class is not thread-safe. Concurrent callers must provide external synchronization.
 */
public class LFUCache {

    private final Map<Integer, NodeValue> keyToValue = new HashMap<>();
    private final Map<Integer, Map<Integer, NodeValue>> frequencyToBucket = new HashMap<>();

    private final int capacity;
    private int size;
    private int minFreq;

    public LFUCache(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
    }

    /**
     * Returns the value stored for {@code key}, or {@code -1} when it is not present.
     *
     * <p>Runs in O(1) average time.
     */
    public int get(int key) {
        NodeValue node = keyToValue.get(key);

        if (node == null) {
            return -1;
        }

        promote(node, node.value());
        return node.value();
    }

    /** Adds or updates the value associated with {@code key}. Runs in O(1) average time. */
    public void put(int key, int value) {
        NodeValue node = keyToValue.get(key);
        if (node != null) {
            promote(node, value);
            return;
        }

        if (size == capacity) {
            evictLeastFrequentlyUsed();
        }

        NodeValue newNode = new NodeValue(key, value, 1);
        keyToValue.put(key, newNode);
        frequencyToBucket.computeIfAbsent(1, ignored -> new LinkedHashMap<>()).put(key, newNode);
        size++;
        minFreq = 1;
    }

    private void promote(NodeValue node, int value) {
        Map<Integer, NodeValue> currentFrequencyValues = frequencyToBucket.get(node.freq());
        currentFrequencyValues.remove(node.key());

        if (currentFrequencyValues.isEmpty()) {
            frequencyToBucket.remove(node.freq());
            if (minFreq == node.freq()) {
                minFreq++;
            }
        }

        NodeValue promotedNode = new NodeValue(node.key(), value, node.freq() + 1);
        keyToValue.put(node.key(), promotedNode);
        frequencyToBucket
                .computeIfAbsent(promotedNode.freq(), ignored -> new LinkedHashMap<>())
                .put(promotedNode.key(), promotedNode);
    }

    private void evictLeastFrequentlyUsed() {
        Map<Integer, NodeValue> leastFrequentValues = frequencyToBucket.get(minFreq);

        Map.Entry<Integer, NodeValue> leastRecentlyUsed =
                leastFrequentValues.entrySet().iterator().next();

        leastFrequentValues.remove(leastRecentlyUsed.getKey());
        if (leastFrequentValues.isEmpty()) {
            frequencyToBucket.remove(minFreq);
        }

        keyToValue.remove(leastRecentlyUsed.getKey());
        size--;
    }

    private record NodeValue(int key, int value, int freq) {}
}
