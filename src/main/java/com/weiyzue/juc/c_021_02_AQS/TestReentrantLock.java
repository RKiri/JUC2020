package com.weiyzue.juc.c_021_02_AQS;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //synchronized (TestReentrantLock.class) {
        try {
            i++;
        } finally {
            lock.unlock();
        }

        /*lock.tryLock();
        lock.lockInterruptibly();*/

        //}


        //synchronized 程序员的丽春院 JUC
    }


}
