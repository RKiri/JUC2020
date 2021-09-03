package com.weiyzue.juc.c_000_threadbasic;

import com.weiyzue.util.SleepHelper;

/**
 * interrupt与sleep() wait() join()
 */
public class T09_Interrupt_and_sync {

    private static Object o = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (o) {
                SleepHelper.sleepSeconds(10);
            }
        });

        t1.start();

        SleepHelper.sleepSeconds(1);

        Thread t2 = new Thread(() -> {
            synchronized (o) {

            }
            System.out.println("t2 end!");
        });

        t2.start();

        SleepHelper.sleepSeconds(1);

        t2.interrupt();
    }
}
