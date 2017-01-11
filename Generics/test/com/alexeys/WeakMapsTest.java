package com.alexeys;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import static org.junit.Assert.*;

/**
 *
 */
public class WeakMapsTest {


    @Test
    public void testWeakMap() throws InterruptedException {
        final Map<Integer, String> map = new WeakHashMap<>();

        // Uncomment this when you want to test regular map performance
        //map = new HashMap<>();

        // Create random string
        String randomString = UUID.randomUUID().toString();

        // Make this string pretty large
        for (int i = 0; i < 10; i++) {
            randomString += randomString;
        }

        // Explode the map
        // Use -Xmx10m so it will happen faster
        for (int i = 0; i < 10_000_000; i++) {
            try {
                map.put(i, randomString);
            }
            // Don't do that at home
            catch (final OutOfMemoryError e) {
                System.out.println("Out of memory: " + i);
                return;
            }
        }

        System.out.println("Size: " + map.size());
    }
}