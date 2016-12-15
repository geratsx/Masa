package com.alexeys;

/**
 * It's preferrable to implement Runnable
 * Since you can implement multiple interfaces, but extend only one
 */
public class PrinterRunnable implements Runnable {
    private final String name;
    private final Integer loops;

    // Bad, don't do that in real code
    private static int counter = 0;

    public PrinterRunnable(String name, Integer loops) {
        this.name = name;
        this.loops = loops;
    }

    public void run() {
        for (int i = 0; i < loops; i++) {
            print(i);
        }
        System.out.println("All done on thread " + this.name);
    }

    private void print(int i) {
        synchronized (this) {
            System.out.println(String.format("%d) %s is working on %d", ++counter, this.name, i));
        }
    }
}
