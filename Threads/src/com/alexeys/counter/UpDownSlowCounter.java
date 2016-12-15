package com.alexeys.counter;

/**
 *
 */
public class UpDownSlowCounter extends UpDownCounter {

    @Override
    public void decrease() {
        synchronized (this) {
            count--;
            System.out.println("Decrease");
        }
    }

    @Override
    public void increment() {
        synchronized (this) {
            count++;
            System.out.println("Increment");
            try {
                System.out.println("Going to sleep");
                Thread.sleep(1000);
                System.out.println("Woke up!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
