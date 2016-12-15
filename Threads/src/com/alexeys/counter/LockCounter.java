package com.alexeys.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by alexey on 15/02/16.
 */
public class LockCounter extends Counter {

    Lock lock = new ReentrantLock();

    public void increment() {
        try {
            lock.lock();
            if (count%PRINT_EVERY == 0) {
                System.out.print(".");
            }

            count++;

            if (count%NEW_LINE_EVERY == 0) {
                System.out.println();
            }
        }
        finally {
            lock.unlock();
        }
    }
}
