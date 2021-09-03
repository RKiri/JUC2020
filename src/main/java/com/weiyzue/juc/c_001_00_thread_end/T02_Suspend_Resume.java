package com.weiyzue.juc.c_001_00_thread_end;

import com.weiyzue.util.SleepHelper;

public class T02_Suspend_Resume {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("go on");
                SleepHelper.sleepSeconds(1);
            }
        });

        t.start();

        SleepHelper.sleepSeconds(5);

        t.suspend();
        SleepHelper.sleepSeconds(3);
        t.resume();

    }
}
