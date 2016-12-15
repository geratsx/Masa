package com.alexeys.counter;

/**
 * Created by alexey on 15/02/16.
 */
public abstract class Counter {


    protected static final int PRINT_EVERY = 100_000;
    protected static final int NEW_LINE_EVERY = 2_000_000;

    @Override
    public String toString() {
        return Integer.toString(count);
    }

    protected int count = 0;

    public abstract void increment();

    public int getCount() {
        return count;
    }
}
