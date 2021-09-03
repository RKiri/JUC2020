/**
 * synchronized
 *
 * @author weiyzue
 */

package com.weiyzue.juc.c_001_sync_basics;

public class T08_01_SyncMethodAndNotSyncMethod {

    private int count = 10;

    public synchronized void m() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public void n() { //访问这个方法的时候不需要上锁
        count++;
    }
}
