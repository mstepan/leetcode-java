package com.github.mstepan.leetcode.other;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class CollatzConjectureMain {

    public static void main(String[] args) throws Exception {

        final int startOfRange = 2;
        final int endOfRange = Integer.MAX_VALUE;

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.nanoTime();

        boolean res = pool.submit(new ConjectureCheckerTask(startOfRange, endOfRange)).get();

        long endTime = System.nanoTime();

        pool.shutdownNow();

        System.out.printf(
                "Collatz Conjecture is %b for range [%d..%d]%n", res, startOfRange, endOfRange);

        Duration elapsedTimeInNanos = Duration.of(endTime - startTime, ChronoUnit.NANOS);

        System.out.printf("time: %d ms%n", TimeUnit.MILLISECONDS.convert(elapsedTimeInNanos));

        System.out.println("CollatzConjectureMain done...");
    }

    private static final class ConjectureCheckerTask extends RecursiveTask<Boolean> {

        private static final int ITERATIONS_COUNT_TO_CHECK_CONJECTURE = 10_000;

        private static final int ELEMENTS_COUNT_THRESHOLD = 10;

        private final long from;
        private final long to;

        public ConjectureCheckerTask(long from, long to) {
            assert from <= to;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Boolean compute() {

            long elementsCount = to - from + 1;

            if (elementsCount < ELEMENTS_COUNT_THRESHOLD) {
                for (long val = from; val <= to; ++val) {
                    if (!isConjectureValid(val)) {
                        return false;
                    }
                }

                return true;
            }

            long mid = from + (to - from) / 2;

            ConjectureCheckerTask left = new ConjectureCheckerTask(from, mid);
            left.fork();

            ConjectureCheckerTask right = new ConjectureCheckerTask(mid + 1, to);

            return right.compute() && left.join();
        }

        private static boolean isConjectureValid(long initialVal) {

            long val = initialVal;

            for (int it = 0; it < ITERATIONS_COUNT_TO_CHECK_CONJECTURE; ++it) {

                if (val < initialVal) {
                    return true;
                }

                if (val == 1L) {
                    return true;
                }
                // even case
                if (val % 2 == 0) {
                    val = val / 2;
                }
                // odd case
                else {
                    val = val * 3 + 1;
                }
            }

            System.err.printf(
                    "Number '%d' wasn't converged into '1' using '%d' iterations",
                    initialVal, ITERATIONS_COUNT_TO_CHECK_CONJECTURE);

            return false;
        }
    }
}
