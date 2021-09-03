package com.weiyzue.juc.c_001_00_thread_end;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.locks.LockSupport;

public class T05_InterruptAndPark {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            System.out.println("1");
            LockSupport.park();
            System.out.println("2");
        });

        t.start();

        SleepHelper.sleepSeconds(1);

        t.interrupt();
    }

}
