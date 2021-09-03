/**
 * synchronized关键字，对某个对象加锁
 *
 * @author weiyzue
 */

package com.weiyzue.juc.c_001_sync_basics;

public class T03_SyncMethod {

    private int count = 10;

    public synchronized void m() { //synchronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }


}
