package com.weiyzue.juc.c_000_threadbasic;

import com.weiyzue.util.SleepHelper;

public class T00_WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                SleepHelper.sleepSeconds(1);
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        //new T1().run();
        new T1().start();
        for (int i = 0; i < 10; i++) {
            SleepHelper.sleepSeconds(1);
            System.out.println("--main");
        }

        SleepHelper.sleepSeconds(1);
    }
}
