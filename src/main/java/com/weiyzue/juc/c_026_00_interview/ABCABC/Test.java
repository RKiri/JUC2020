package com.weiyzue.juc.c_026_00_interview.ABCABC;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 问题：用三个(N)个线程，按顺序输出ABC ABC ABC
 * 还有一些小bug需要修正
 */
public class Test {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cA = lock.newCondition();
    private static Condition cB = lock.newCondition();
    private static Condition cC = lock.newCondition();

    private static CountDownLatch latchB = new CountDownLatch(1);
    private static CountDownLatch latchC = new CountDownLatch(1);

    public static void main(String[] args) {
        //思路：三个线程，三个不同的队列（Condition)，A唤醒B , B唤醒C
        Thread threadA = new Thread(() -> {

            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("A");
                    cB.signal();
                    if (i == 0) latchB.countDown();
                    cA.await();
                }

                cB.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


            //先输出，然后叫醒B
        }, "Thread A");

        Thread threadB = new Thread(() -> {
            //先等待唤醒
            try {
                latchB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("B");
                    cC.signal();
                    if (i == 0) latchC.countDown();
                    cB.await();
                }

                cC.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            //唤醒后输出，然后叫醒C
        }, "Thread B");
        Thread threadC = new Thread(() -> {
            try {
                latchC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //先等待唤醒


            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print("C");
                    cA.signal();
                    cC.await();
                }

                cA.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

            //唤醒后输出，然后叫醒A
        }, "Thread C");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
