/*
Condition本质是锁资源上不同的等待队列
 */
package com.weiyzue.juc.c_026_00_interview.A1B2C3;


import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T09_00_lock_condition {

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition(); //队列
        Condition conditionT2 = lock.newCondition();

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {

                for (char c : aI) {
                    System.out.print(c);
                    conditionT2.signal();
                    conditionT1.await();
                }

                conditionT2.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {

                for (char c : aC) {
                    System.out.print(c);
                    latch.countDown();
                    conditionT1.signal();

                    conditionT2.await();
                }

                conditionT1.signal();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();
    }
}


