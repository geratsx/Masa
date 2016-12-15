package com.alexeys;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class HBlockingQueueTest {


    private static final int PING_PONGS = 5;
    private static final int MAX_MILLIS_BETWEEN_PINGS = 1000;

    /**
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testBlockingQueue() throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Random random = new Random();

        BlockingQueue<String> channelIn = new LinkedBlockingQueue<>();
        BlockingQueue<String> channelOut = new LinkedBlockingQueue<>();

        // Ping sends on channelIn and receives on channelOut
        Future<Boolean> pingResult = pool.submit(() -> {
            try {
                // Initialize communication
                channelIn.put("Ping-pong start ->");
                for (int i = 0; i < PING_PONGS; i++) {
                    String pong = channelOut.take();
                    System.out.println(Thread.currentThread().getName() + ": " + pong);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(MAX_MILLIS_BETWEEN_PINGS));
                    channelIn.put(System.currentTimeMillis() + ": Ping ->");
                }
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        });

        // Pong sends on channelOut and receives on channelIn
        Future<Boolean> pongResult = pool.submit(() -> {
            try {
                for (int i = 0; i < PING_PONGS; i++) {
                    String ping = channelIn.take();
                    System.out.println(Thread.currentThread().getName() + ": " + ping);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(MAX_MILLIS_BETWEEN_PINGS));
                    channelOut.put(System.currentTimeMillis() + ": <- Pong");
                }
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        });

        // Wait for both to finish playing
        final boolean b = pingResult.get() && pongResult.get();
    }
}
