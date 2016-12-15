package com.alexeys.counter;

/**
 * Created by alexey on 15/02/16.
 */
public class SyncBlockCounter extends Counter {

    @Override
    public void increment() {
        if (count%PRINT_EVERY == 0) {
            System.out.print(".");
        }

        synchronized (this) {
            count++;
        }

        if (count%NEW_LINE_EVERY == 0) {
            System.out.println();
        }
    }
}
