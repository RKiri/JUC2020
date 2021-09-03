package com.weiyzue.insidesync;

import com.weiyzue.util.SleepHelper;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.LockSupport;

public class Test {

    private static class T {

    }

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            LockSupport.park();

            System.out.println("hello");

            LockSupport.park();

            System.out.printf("hhhhh");
        });

        t.start();

        SleepHelper.sleepSeconds(1);

        LockSupport.unpark(t);
    }
}
