package com.weiyzue.juc.c_018_00_AtomicXXX;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.atomic.AtomicStampedReference;

public class T09_TestAtomicStampedReference {

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


    static AtomicStampedReference<Order> orderRef = new AtomicStampedReference<>(new Order(), 0);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Order old = orderRef.getReference();
                int stamp = orderRef.getStamp();

                Order o = new Order();
                o.sequence = old.sequence + 1;
                o.time = System.currentTimeMillis();

                orderRef.compareAndSet(old, o, stamp, stamp + 1);


            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(orderRef.getReference());
    }
}
