package com.weiyzue.juc.c_001_sync_basics;

import com.weiyzue.util.SleepHelper;

public class T00_01_WhatIsLock {
    private static Object o = new Object();//用来被锁定的对象 当成一把锁

    public static void main(String[] args) {
        Runnable r = () -> {
            synchronized (o) {//锁定某个对象才能执行这句话
                System.out.println(Thread.currentThread().getName() + " start!");//打印线程名字
                SleepHelper.sleepSeconds(2);
                System.out.println(Thread.currentThread().getName() + " end!");
            }
        };

        //第一个线程2s在一颗CPU 第二个线程2s在另一颗CPU..总时长2s
        for (int i = 0; i < 3; i++) {
            new Thread(r).start();
        }
    }
}
