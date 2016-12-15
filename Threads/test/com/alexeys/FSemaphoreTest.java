package com.alexeys;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created by alexey on 18/02/16.
 */
public class FSemaphoreTest {

    private static final int THREADS_NUMBER = 4;

    /**
     * Semaphore is like a lock, but it allows X threads to access resource at the same time
     * Semaphore(1) acts just as a normal lock would
     */
    @Test
    public void testSemaphore() throws InterruptedException {

        int tasksNumber = 10;
        ExecutorService pool = Executors.newFixedThreadPool(THREADS_NUMBER);
        CountDownLatch latch = new CountDownLatch(tasksNumber);
        Semaphore semaphore = new Semaphore(THREADS_NUMBER / 2);


        for (int i = 0; i < tasksNumber; i++) {
            pool.submit(() -> {
                try {
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName() + " is going to sleep");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    latch.countDown();
                }
            });
        }

       // latch.await();
        System.out.println(tasksNumber + " tasks finished");
    }
}
