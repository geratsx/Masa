package com.alexeys;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Why threads?
 * Concurrency
 * Parallelism (it's not concurrency?)
 * Doing things later
 * Doing things in the background
 */
public class AThreadsTest {


    private static final int NUMBER_OF_THREADS = 4;

    /**
     * Let's start 4 threads
     *
     * @throws InterruptedException
     */
    @Test
    public void testThreadRun() throws InterruptedException {
        int loops = 100;
        Thread t1 = new PrinterThread("a", loops);
        Thread t2 = new PrinterThread("b", loops);
        Thread t3 = new PrinterThread("c", loops);
        Thread t4 = new PrinterThread("d", loops);

        t1.run();
        t2.run();
        t3.run();
        t4.run();

        // Did we run in parallel?
    }

    /**
     * It's confusing, but you need to call .start() to run in parallel
     *
     * @throws InterruptedException
     */
    @Test
    public void testThreadStartNoJoin() throws InterruptedException {

        int loops = 1000;
        Thread t1 = new PrinterThread("a", loops);
        Thread t2 = new PrinterThread("b", loops);
        Thread t3 = new PrinterThread("c", loops);
        Thread t4 = new PrinterThread("d", loops);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // How many lines did we print?
        System.out.println("All done");
    }

    /**
     * You need to call .join() to ensure current thread will wait for another thread to finish
     *
     * @throws InterruptedException
     */
    @Test
    public void testThreadStartJoin() throws InterruptedException {

        int loops = 1000;

        Thread t1 = new PrinterThread("a", loops);
        Thread t2 = new PrinterThread("b", loops);
        Thread t3 = new PrinterThread("c", loops);
        Thread t4 = new PrinterThread("d", loops);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Current thread will wait until t1, t2, t3 and t4 have finished
        t1.join();
        t2.join();
        t3.join();
        t4.join();

        // Now we're getting somewhere!
        System.out.println("All done");
    }

    /**
     * Same as before, but now the logic is inside Runnable implementation
     *
     * @throws InterruptedException
     */
    @Test
    public void testRunnable() throws InterruptedException {

        int loops = 500;
        Runnable r1 = new PrinterRunnable("a", loops);
        Runnable r2 = new PrinterRunnable("b", loops);
        Runnable r3 = new PrinterRunnable("c", loops);
        Runnable r4 = new PrinterRunnable("d", loops);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);
        Thread t4 = new Thread(r4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
    }

    /**
     * It's important to understand the difference between data corruption
     * and structure corruption
     * Here I'm going to demonstrate how the datastructure itself can be corrupted
     *
     * @throws InterruptedException
     */
    @Test
    public void testMapCorruption() throws InterruptedException {

        // Created map that is not thread-safe
        Map<String, String> unsafeMap = new HashMap<>();

        List<Runnable> runnableList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Create runables
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            runnableList.add(new GeneratorRunnable("Thread" + i, unsafeMap));
        }

        long start = System.currentTimeMillis();
        for (Runnable r : runnableList) {
            Thread t = new Thread(r);
            t.start();
            threads.add(t);
        }

        // Wait on threads
        for (Thread t : threads) {
            t.join();
        }
        printTime(start);

        // What happened here?
        System.out.println("The size of the map is : " + unsafeMap.size());

        int i = getIteratorCount(unsafeMap.entrySet().iterator());

        // And what's this?
        System.out.println("Number of entries: " + i);
    }


    /**
     * Most simple way to solve this issue is to use a synchronized map
     *
     * @throws InterruptedException
     */
    @Test
    public void testSyncProtectsMap() throws InterruptedException {

        Map<String, String> safeMap = Collections.synchronizedMap(new HashMap<>());

        List<Runnable> runnableList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            runnableList.add(new GeneratorRunnable("Thread" + i, safeMap));
        }

        long start = System.currentTimeMillis();
        for (Runnable r : runnableList) {
            Thread t = new Thread(r);
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            t.join();
        }
        printTime(start);
        System.out.println("The size of the map is : " + safeMap.size());

        System.out.println("Number of entries: " + getIteratorCount(safeMap.entrySet().iterator()));
    }

    /**
     * But even better way is a ConcurrentHashMap, that produces better performance
     *
     * @throws InterruptedException
     */
    @Test
    public void testConcurrentProtectsMap() throws InterruptedException {

        Map<String, String> safeMap = new ConcurrentHashMap<>();

        List<Runnable> runnableList = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            runnableList.add(new GeneratorRunnable("Thread" + i, safeMap));
        }

        long start = System.currentTimeMillis();
        for (Runnable r : runnableList) {
            Thread t = new Thread(r);
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            t.join();
        }
        printTime(start);
        System.out.println("The size of the map is : " + safeMap.size());
    }

    /**
     * Let's compare the execution times of three approaches
     *
     * @throws InterruptedException
     */
    @Test
    public void testAllThree() throws InterruptedException {
        testMapCorruption();
        testSyncProtectsMap();
        testConcurrentProtectsMap();
    }


    /**
     * Print time that passed from start to current time
     *
     * @param start
     */
    private void printTime(long start) {
        System.out.println("It took " + (System.currentTimeMillis() - start) + " millis to run this");
    }

    private int getIteratorCount(Iterator iterator) {
        int i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
    }
}