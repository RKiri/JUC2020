/**
 * 同步方法和非同步方法时候可以同时调用？
 *
 * @author weiyzue
 */

package com.weiyzue.juc.c_001_sync_basics;

public class T08_02_SyncMethodAndNotSyncMethod {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        T08_02_SyncMethodAndNotSyncMethod t = new T08_02_SyncMethodAndNotSyncMethod();
		
		/*new Thread(()->t.m1(), "t1").start();
		new Thread(()->t.m2(), "t2").start();*/

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
		
		/*
		//1.8֮ǰ��д��
		new Thread(new Runnable() {

			@Override
			public void run() {
				t.m1();
			}
			
		});
		*/

    }

}
