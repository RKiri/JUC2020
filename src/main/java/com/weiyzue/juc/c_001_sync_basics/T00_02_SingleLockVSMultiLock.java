package com.weiyzue.juc.c_001_sync_basics;

import com.weiyzue.util.SleepHelper;

public class T00_02_SingleLockVSMultiLock {
    private static Object o1 = new Object();
    private static Object o2 = new Object();
    private static Object o3 = new Object();

    public static void main(String[] args) {
        Runnable r1 = () -> {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " start!");
                SleepHelper.sleepSeconds(2);
                System.out.println(Thread.currentThread().getName() + " end!");
            }
        };

        Runnable r2 = () -> {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " start!");
                SleepHelper.sleepSeconds(2);
                System.out.println(Thread.currentThread().getName() + " end!");
            }
        };

        Runnable r3 = () -> {
            synchronized (o3) {
                System.out.println(Thread.currentThread().getName() + " start!");
                SleepHelper.sleepSeconds(2);
                System.out.println(Thread.currentThread().getName() + " end!");
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();
    }
}
