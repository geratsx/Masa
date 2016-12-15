package com.alexeys;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Read more about this here:
 * http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/
 */
public class DCallableTest {

    /**
     * Callable is just like runnable, but that can return a value
     */
    @Test
    public void testCallable() throws InterruptedException, ExecutionException {
        // With generics we specify what the Callable should return
        Callable<String> callable = () -> {
            // Calculate UUID 1000 times
            String uuid = UUID.randomUUID().toString();
            for (int i = 0; i < 999; i++) {
                uuid = UUID.randomUUID().toString();
                TimeUnit.MILLISECONDS.sleep(1);
            }
            return uuid;
        };

        ExecutorService pool = Executors.newSingleThreadExecutor();

        // Result of Callable submission is Future
        // Future allows to check the status of the task
        Future<String> result = pool.submit(callable);

        // This is unnecessary in real code, as result.get() will block
        // But I would like to demonstrate how isDone() lets us query the status
        while (!result.isDone()) {
            System.out.println("Not done yet");
            TimeUnit.MILLISECONDS.sleep(100);
        }

        System.out.println("Finished with result " + result.get());
    }


    class WeatherCallable implements Callable<String> {

        private static final String API_KEY = "AIzaSyDkYg9QBGc9--uwFdaeR8-Sjo8JeVTe7UM";
        private final String cityName;

        public WeatherCallable(String cityName) {
            this.cityName = cityName;
        }

        @Override
        public String call() throws Exception {
            StringBuilder result = new StringBuilder();
            URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?query=restaurants+in+" + this.cityName + "&key=" + API_KEY);
            Long start = System.currentTimeMillis();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            try {
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                br.lines().forEach((line) -> {
                    result.append(line);
                });

            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            System.out.println("Request for " + this.cityName + " took " + (System.currentTimeMillis() - start));
            return result.toString();
        }
    }

    /**
     * Calls an actual API to get some weather data
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testLongRunningCallableSingleThread() throws ExecutionException, InterruptedException {
        Callable<String> telAvivCallable = new WeatherCallable("Tel+Aviv");
        Callable<String> londonCallable = new WeatherCallable("London");

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Long start = System.currentTimeMillis();

        Future<String> telAvivResult = pool.submit(telAvivCallable);
        Future<String> londonResult = pool.submit(londonCallable);


        System.out.println("Tel Aviv: " + telAvivResult.get());
        System.out.println("London: " + londonResult.get());

        System.out.println("Took " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * Notice how much time it takes for the second call
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testLongRunningCallableMultiThread() throws ExecutionException, InterruptedException {
        Callable<String> telAvivCallable = new WeatherCallable("Tel+Aviv");
        Callable<String> londonCallable = new WeatherCallable("London");

        ExecutorService pool = Executors.newFixedThreadPool(10);
        Long start = System.currentTimeMillis();

        Future<String> telAvivResult = pool.submit(telAvivCallable);
        Future<String> londonResult = pool.submit(londonCallable);


        System.out.println("Tel Aviv: " + telAvivResult.get());
        System.out.println("London: " + londonResult.get());

        System.out.println("Took " + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * It's a good practice to specify a timeout, as task may take too long to execute
     */
    @Test
    public void testCallableTimeout() {
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future<String> result = pool.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "Done";
        });

        try {
            result.get(2, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
