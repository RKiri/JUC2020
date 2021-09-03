package com.weiyzue.juc.c_001_02_FalseSharing;


import sun.misc.Contended;
//注意：运行这个小程序的时候，需要加参数：-XX:-RestrictContended
import java.util.concurrent.CountDownLatch;

public class T05_Contended {
    public static long COUNT = 10_0000_0000L;

    //@Contended  //只有1.8起作用 , 保证x位于单独一行中
    private static class T {
        public long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[0].x = i;
            }

            latch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < COUNT; i++) {
                arr[1].x = i;
            }

            latch.countDown();
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}
