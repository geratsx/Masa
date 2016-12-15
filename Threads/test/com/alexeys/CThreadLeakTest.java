package com.alexeys;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class CThreadLeakTest {

    /**
     * Beware, that each thread takes memory, even if its doing nothing
     * This test demonstrates how quickly you can run out of memory when not using thread carefully
     */
    @Test
    public void testLeakThreads() {
        Runnable r = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " is going to sleep");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5_000; i++) {
            Thread t = new Thread(r);
            t.start();
        }
    }


    /**
     * By using thread pool you will not run out of memory
     * @throws InterruptedException
     */
    @Test
    public void testExecutor() throws InterruptedException {
        final int tasksNumber = 5_000;
        ExecutorService executor = Executors.newFixedThreadPool(300);

        // Latch allows to wait us X counts
        CountDownLatch latch = new CountDownLatch(tasksNumber);


        for (int i = 0; i < tasksNumber; i++) {
            executor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is going to sleep");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        executor.submit(() -> {
            System.out.println("Hello");
        });

        System.out.println(String.format("Issued %d tasks and waiting now", tasksNumber));
        latch.await();
    }
}
