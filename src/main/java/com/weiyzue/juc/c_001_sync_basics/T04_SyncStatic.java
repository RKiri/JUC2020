/**
 * synchronized
 *
 * @author weiyzue
 */

package com.weiyzue.juc.c_001_sync_basics;

public class T04_SyncStatic {

    private static int count = 10;

    public synchronized static void m() { //等同于synchronized(XX.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized (T04_SyncStatic.class) { //考虑一下这里写synchornized(this)是否可以？
            count--;
        }
    }

    //m方法执行的时刻，n方法能不能同时执行？
    public synchronized void n() {

    }

}
