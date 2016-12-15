package com.alexeys;

import com.alexeys.counter.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BSyncsTest {

    private static final int NUMBER_OF_THREADS = 4;
    private static final int loops = 20_000_000;

    /**
     * Simple counter that doesn't use protection at all
     * Let's see how it works
     * @throws InterruptedException
     */
    @Test
    public void testDataCorruption() throws InterruptedException {
        Counter globalCounter = new SimpleCounter();
        doCount("testDataCorruption", globalCounter);
    }

    /**
     * SyncCounter synchronizes the whole method
     * @throws InterruptedException
     */
    @Test
    public void testSyncCounter() throws InterruptedException {
        Counter globalCounter = new SyncCounter();
        doCount("testSyncCounter", globalCounter);
    }

    /**
     * SyncBlockCounter synchronizes only part of the method that needs synchronization
     * @throws InterruptedException
     */
    @Test
    public void testSyncBlockCounter() throws InterruptedException {
        Counter globalCounter = new SyncBlockCounter();
        doCount("testSyncBlockCounter", globalCounter);
    }

    /**
     * This counter uses a Lock object to ensure thread safety
     * @throws InterruptedException
     */
    @Test
    public void testLockCounter() throws InterruptedException {
        Counter globalCounter = new LockCounter();
        doCount("testLockCounter", globalCounter);
    }

    @Test
    public void testAll() throws InterruptedException {
        testDataCorruption();

        testSyncCounter();

        testSyncBlockCounter();

        testLockCounter();
    }

    /**
     * Note how we use same lock on two different methods
     * @throws InterruptedException
     */
    @Test
    public void testSameSyncBlock() throws InterruptedException {

        UpDownCounter counter = new UpDownSlowCounter();
        // Start one thread, that does calls increase
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });
        t1.start();


        // Let the first thread acquire the lock fo sure
        //Same as Thread.sleep(100), but better
        TimeUnit.MILLISECONDS.sleep(100);

        // Start another thread, that does decrease

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.decrease();
            }
        });


        // This should wait until t1 finishes
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                counter.increment();
            }
        });

        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    /**
     * Receives any counter, and passes it to threads
     * @param name
     * @param globalCounter
     * @throws InterruptedException
     */
    private void doCount(String name, Counter globalCounter) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();


        long start = System.currentTimeMillis();
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            Runnable r = new CounterRunnable("Runnable"+i, loops, globalCounter);
            Thread t = new Thread(r);

            // Add
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        printTime(name, start);

        System.out.println("Threads increased counter to: " + globalCounter);
    }

    private void printTime(String name, long start) {
        System.out.println(name + " took " + (System.currentTimeMillis() - start) + " millis to run this");
    }
}