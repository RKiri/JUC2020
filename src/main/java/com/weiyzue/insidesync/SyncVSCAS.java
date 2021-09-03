package com.weiyzue.insidesync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 这个程序用于测试synchronized和CAS锁的效率问题
 * 在高争用 高耗时的环境下synchronized效率更高
 * 在低争用 低耗时的环境下CAS效率更高
 * 其他情况需要测试决定
 *
 * @author http://weiyzue.com 马士兵
 */
public class SyncVSCAS {
    private static Object o = new Object();
    private static Lock lock = new ReentrantLock();

    private static long count = 0;
    private static final int LOOP = 100_0000;

    private static Thread[] threads = new Thread[200];

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (o) {
                    for (int j = 0; j < LOOP; j++) {
                        count = count + 1;
                    }

                    milisleep();
                }
            });
        }

        long start = System.nanoTime();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.nanoTime();

        System.out.println(end - start);

        //==================================================

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                try {
                    lock.lock();
                    for (int j = 0; j < LOOP; j++) {
                        count = count + 1;
                    }
                    milisleep();
                } finally {
                    lock.unlock();
                }
            });
        }

        long start1 = System.nanoTime();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end1 = System.nanoTime();

        System.out.println(end1 - start1);
    }

    private static void milisleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
