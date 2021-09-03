package com.weiyzue.juc.c_011_01_syncwaitnotify;

import com.weiyzue.util.SleepHelper;

public class T01_TestWaitNotify {
    private static Object o = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (o) {
                System.out.println("starting ...");
                SleepHelper.sleepSeconds(1);
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end!");
            }
        }).start();


        SleepHelper.sleepSeconds(5);

        synchronized (o) {
            //o.notify();
            o.notifyAll();
        }

    }
}
