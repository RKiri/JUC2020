package com.weiyzue.juc.c_018_00_AtomicXXX;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T07_TestAtomicReference {

    private static class Order {
        long sequence;
        long time;

        @Override
        public String toString() {
            return "Order{" +
                    "sequence=" + sequence +
                    ", time=" + time +
                    '}';
        }
    }


    static Order order = new Order();
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(() -> {
                lock.lock();
                try {
                    Order old = order;
                    order = new Order();
                    order.sequence = old.sequence + 1;
                    order.time = System.currentTimeMillis();
                } finally {
                    lock.unlock();
                }


            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(order);
    }
}
