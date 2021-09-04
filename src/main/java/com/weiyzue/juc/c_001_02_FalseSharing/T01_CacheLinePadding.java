package com.weiyzue.juc.c_001_02_FalseSharing;

import java.util.concurrent.CountDownLatch;

public class T01_CacheLinePadding {
    public static long COUNT = 10_0000_0000L;//循环10亿次

    private static class T {
        //加7个没有意义的long类型数字 作为填充
        //一定不会和另外x位于同一行
        private long p1, p2, p3, p4, p5, p6, p7;
        public long x = 0L;//8字节 bytes
        private long p9, p10, p11, p12, p13, p14, p15;
    }

    public static T[] arr = new T[2];//new T类型只有两个元素数组，都有x 一共8字节 对象加起来没多少字节

    static {
        //大概率位于同一缓存行
        //这行在两个线程、CPU内 其中一个只访问x1 但会将整个x1、x2缓存行缓存过来
        //缓存一致性协议
        //缓存行失效 修改 和另外一个缓存行保持一致
        //多线程修改同一行 会互相干扰
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(2);

        //不断修改arr[0].x
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

        //计算线程执行完消耗时间
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        latch.await();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}
