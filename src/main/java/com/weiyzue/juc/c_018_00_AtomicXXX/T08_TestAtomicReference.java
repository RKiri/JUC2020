package com.weiyzue.juc.c_018_00_AtomicXXX;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.atomic.AtomicReference;

public class T08_TestAtomicReference {

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


    static AtomicReference<Order> order = new AtomicReference<>(new Order());

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Order old = order.get();
                Order o = new Order();
                o.sequence = old.sequence + 1;
                o.time = System.currentTimeMillis();

                order.compareAndSet(old, o);


            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(order);
    }
}
