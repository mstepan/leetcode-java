package com.github.mstepan.leetcode.concurrency;

import java.util.concurrent.SynchronousQueue;
import java.util.function.IntConsumer;

/**
 * 1116. Print Zero Even Odd
 *
 * <p>https://leetcode.com/problems/print-zero-even-odd/description
 */
public class PrintZeroEvenOdd {

    public static void main(String[] args) throws Exception {

        ZeroEvenOdd obj = new ZeroEvenOdd(10);

        IntConsumer valuePrinter =
                (value) -> {
                    System.out.print(value);
                    System.out.flush();
                };

        Thread zeroThread =
                Thread.ofPlatform()
                        .start(
                                () -> {
                                    try {
                                        obj.zero(valuePrinter);
                                    } catch (InterruptedException interEx) {
                                        Thread.currentThread().interrupt();
                                    }
                                });

        Thread oddThread =
                Thread.ofPlatform()
                        .start(
                                () -> {
                                    try {
                                        obj.odd(valuePrinter);
                                    } catch (InterruptedException interEx) {
                                        Thread.currentThread().interrupt();
                                    }
                                });
        Thread evenThread =
                Thread.ofPlatform()
                        .start(
                                () -> {
                                    try {
                                        obj.even(valuePrinter);
                                    } catch (InterruptedException interEx) {
                                        Thread.currentThread().interrupt();
                                    }
                                });

        zeroThread.join();
        oddThread.join();
        evenThread.join();
    }
}

class ZeroEvenOdd {

    private static final int STOP_MARKER = -1;
    private static final int REPLY_MARKER = 0;

    // This counter is thread-safe because it's only updated by a single thread and its value is
    // simply read by other threads, so synchronization is not necessary.
    private int curValue;
    private int n;

    private final SynchronousQueue<Integer> oddQueue = new SynchronousQueue<>();
    private final SynchronousQueue<Integer> evenQueue = new SynchronousQueue<>();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            try {
                ++curValue;
                if (curValue > n) {
                    // terminate both threads: odd and even

                    oddQueue.put(STOP_MARKER);
                    oddQueue.take();

                    evenQueue.put(STOP_MARKER);
                    evenQueue.take();

                    break;
                } else {
                    printNumber.accept(0);

                    // even case
                    if ((curValue & 1) == 0) {
                        evenQueue.put(curValue);
                        evenQueue.take();
                    }
                    // odd case
                    else {
                        oddQueue.put(curValue);
                        oddQueue.take();
                    }
                }
            } catch (InterruptedException interEx) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            try {
                int value = oddQueue.take();

                if (value == STOP_MARKER) {
                    oddQueue.put(REPLY_MARKER);
                    break;
                } else {
                    printNumber.accept(value);
                    oddQueue.put(REPLY_MARKER);
                }
            } catch (InterruptedException interEx) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            try {
                int value = evenQueue.take();

                if (value == STOP_MARKER) {
                    evenQueue.put(REPLY_MARKER);
                    break;
                } else {
                    printNumber.accept(value);
                    evenQueue.put(REPLY_MARKER);
                }
            } catch (InterruptedException interEx) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
