package com.alexeys;

import java.util.Map;
import java.util.UUID;

/**
 * Created by alexey on 10/02/16.
 */
public class GeneratorRunnable implements Runnable {

    private final String name;
    private final Map<String, String> map;

    public GeneratorRunnable(String name, Map<String, String> map) {
        this.name = name;
        this.map = map;
    }

    private void doWork() {

        for (int i = 0; i < 100_000; i++) {
            String uuid = UUID.randomUUID().toString();
            // Let's make sure that we didn't generate same UUID, no matter probability
            if (!map.containsKey(uuid)) {
                map.put(uuid, name);

                // Let's make sure that no other thread has replaced the value
                if (map.get(uuid) != null && !map.get(uuid).equals(name)) {
                    System.out.println("Overridden value");
                }
            }
            else {
                System.out.println("Duplicate key");
            }
        }

        System.out.println(String.format("%s finished", this.name));
    }

    @Override
    public void run() {
        doWork();
    }
}
