/**
 * ThreadLocal�ֲ߳̾�����
 *
 * @author ��ʿ��
 */
package com.weiyzue.juc.c_022_RefTypeAndThreadLocal;

import com.weiyzue.util.SleepHelper;

public class ThreadLocal1 {
    static Person p = new Person();

    public static void main(String[] args) {

        new Thread(() -> {
            SleepHelper.sleepSeconds(1);
            p.name = "lisi";
        }).start();

        new Thread(() -> {
            SleepHelper.sleepSeconds(2);
            System.out.println(p.name);
        }).start();

    }
}

class Person {
    String name = "zhangsan";
}
