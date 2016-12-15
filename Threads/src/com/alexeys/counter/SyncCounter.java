package com.alexeys.counter;

/**
 * Created by alexey on 15/02/16.
 */
public class SyncCounter extends Counter {

    public synchronized void increment() {
        if (count%PRINT_EVERY == 0) {
            System.out.print(".");
        }

        count++;

        if (count%NEW_LINE_EVERY == 0) {
            System.out.println();
        }
    }
}
