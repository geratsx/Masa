package com.alexeys;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by alexey on 18/02/16.
 */
public class GAtomicTest {

    private static final int THREADS_NUMBER = 10;

    @Test
    public void testAtomicInteger() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService pool = Executors.newFixedThreadPool(THREADS_NUMBER);

        AtomicInteger count = new AtomicInteger(0);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            pool.submit(() -> {
                try {
                    for (int j = 0; j < 1_000_000; j++) {
                        count.incrementAndGet();
                    }
                }
                finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        System.out.println("Count is " + count);
    }


}
