/**
 * 对比前一个小程序，分析一下这个程序的输出
 *
 * @author weiyzue
 */

package com.weiyzue.juc.c_001_sync_basics;

public class T07_SyncAndVolatile_02 implements Runnable {

    private int count = 10;

    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            T07_SyncAndVolatile_02 t = new T07_SyncAndVolatile_02();
            new Thread(t, "THREAD" + i).start();
        }
    }

}
