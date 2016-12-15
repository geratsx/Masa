package com.alexeys;

import com.alexeys.counter.Counter;

/**
 * Created by alexey on 15/02/16.
 */
public class CounterRunnable implements Runnable {
    private final String name;
    private final int loops;
    private Counter globalCounter;

    public CounterRunnable(String s, int i, Counter globalCounter) {
        this.name = s;
        this.loops = i;
        this.globalCounter = globalCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            globalCounter.increment();
        }
    }
}
