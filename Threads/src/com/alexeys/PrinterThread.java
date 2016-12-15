package com.alexeys;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by alexey on 14/02/16.
 */
public class PrinterThread extends Thread {
    private final String name;
    private final Integer loops;

    private final static Boolean lock = true;

    // Bad, don't do that in real code
    private static int counter = 0;

    public PrinterThread(String name, Integer loops) {
        this.name = name;
        this.loops = loops;
    }

    public void run() {
        for (int i = 0; i < loops; i++) {
            print(i);
        }
        System.out.println(String.format("%s has finished", this.name));
    }

    private void print(int i) {
        synchronized (lock) {
            System.out.println(String.format("%d) %s is working on %d", ++counter, this.name, i));
        }
    }
}
