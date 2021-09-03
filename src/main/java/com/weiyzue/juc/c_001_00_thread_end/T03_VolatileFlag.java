package com.weiyzue.juc.c_001_00_thread_end;

import com.weiyzue.util.SleepHelper;

public class T03_VolatileFlag {

    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            long i = 0L;
            while (running) {
                //wait recv accept
                i++;
            }

            System.out.println("end and i = " + i); //4168806262 4163032200
        });

        t.start();

        SleepHelper.sleepSeconds(1);

        running = false;
    }
}
