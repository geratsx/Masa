package com.alexeys;

import org.junit.Test;

import java.util.concurrent.*;


public class ESchedulerTest {

    private static final int MAX_THREADS = 4;

    /**
     * You can schedule a task to execute after a certain delay
     * @throws InterruptedException
     */
    @Test
    public void testScheduler() throws InterruptedException, ExecutionException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(MAX_THREADS);


        ScheduledFuture<String> result = pool.schedule(() -> {
            System.out.println("Schedule executed");
            return "A";
        }, 5, TimeUnit.SECONDS);

        while (!result.isDone()) {
            System.out.println(result.getDelay(TimeUnit.SECONDS) + " seconds until done");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void testSchedule() throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(MAX_THREADS);

        final long every = 1;
        final long initialDelay = 2;
        pool.scheduleAtFixedRate(() -> {
            System.out.println("Doing something");
        }, initialDelay, every, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(10);
    }
}
