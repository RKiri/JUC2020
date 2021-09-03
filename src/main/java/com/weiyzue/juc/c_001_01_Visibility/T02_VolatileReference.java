/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
package com.weiyzue.juc.c_001_01_Visibility;

import com.weiyzue.util.SleepHelper;

public class T02_VolatileReference {

    private static class A {
        boolean running = true;

        void m() {
            System.out.println("m start");
            while (running) {
            }
            System.out.println("m end!");
        }
    }

    private volatile static A a = new A();

    public static void main(String[] args) {
        new Thread(a::m, "t1").start();
        SleepHelper.sleepSeconds(1);
        a.running = false;
    }


}
