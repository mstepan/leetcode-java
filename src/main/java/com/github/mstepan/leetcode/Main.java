package com.github.mstepan.leetcode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

        Map<Integer, Integer> map = new ConcurrentHashMap<>();

        final int threadsCount = 200;
        final int iterationsCount = 10_000;
        final int dummyKey = 13;

        ExecutorService pool = Executors.newFixedThreadPool(threadsCount);
        CountDownLatch allCompleted = new CountDownLatch(threadsCount);

        try {

            for (int i = 0; i < threadsCount; ++i) {
                pool.submit(
                        () -> {
                            try {
                                for (int it = 0;
                                        it < iterationsCount
                                                && !Thread.currentThread().isInterrupted();
                                        ++it) {

                                    /*
                                    Check of 'ConcurrentHashMap.compute(...)' method is fully thread safe.
                                     */

                                    map.compute(
                                            dummyKey,
                                            (key, count) -> count == null ? 1 : count + 1);
                                }
                            } finally {
                                allCompleted.countDown();
                            }
                        });
            }

            allCompleted.await();

            int expectedCount = threadsCount * iterationsCount;

            if (expectedCount == map.get(dummyKey)) {
                System.out.println("All good");
            } else {
                throw new IllegalStateException(
                        "Incorrect value detected, expected = %d, found = %d"
                                .formatted(expectedCount, map.get(dummyKey)));
            }

        } finally {
            pool.shutdownNow();
            boolean wasTerminated = pool.awaitTermination(1L, TimeUnit.SECONDS);
            if (!wasTerminated) {
                System.err.println("Pool wasn't terminated, timed out.");
            }
        }

        System.out.println("Main done...");
    }
}
