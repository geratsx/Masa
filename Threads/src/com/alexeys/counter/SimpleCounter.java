package com.alexeys.counter;

import com.alexeys.counter.Counter;

/**
 * Created by alexey on 15/02/16.
 */
public class SimpleCounter extends Counter {
    @Override
    public void increment() {
        if (count%PRINT_EVERY == 0) {
            System.out.print(".");
        }

        count++;

        if (count%NEW_LINE_EVERY == 0) {
            System.out.println();
        }
    }
}
