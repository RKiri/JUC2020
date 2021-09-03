package com.weiyzue.insidesync;

/**
 * 这个程序用于测试线程数量很多，发生争用的时候偏向锁的影响
 * 可以通过-XX:-UseBiasedLocking关闭偏向锁
 * 通过打开和关闭偏向锁，可以感觉到，线程数量非常高的时候，关闭偏向锁会更合适
 *
 * @author http://weiyzue.com 马士兵
 */
public class TestBiasedLocking {
    private static Object o = new Object();
    private static long count = 0;

    private static Thread[] threads = new Thread[25000];

    static {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (o) {
                    for (int j = 0; j < 100_0000; j++) {
                        count = count + 1;
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws Exception {
        long start = System.nanoTime();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.nanoTime();

        System.out.println(end - start);
    }
}
