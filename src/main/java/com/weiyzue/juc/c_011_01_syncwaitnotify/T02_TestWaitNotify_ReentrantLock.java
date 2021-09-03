package com.weiyzue.juc.c_011_01_syncwaitnotify;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.locks.LockSupport;

public class T02_TestWaitNotify_ReentrantLock {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("starting ...");
            SleepHelper.sleepSeconds(1);
            LockSupport.park();
            System.out.println("end!");

        });

        t.start();

        SleepHelper.sleepSeconds(5);

        LockSupport.unpark(t);

        //LockSupport.park(new Object());

    }
}
