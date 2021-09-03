/**
 * ThreadLocal�ֲ߳̾�����
 * <p>
 * ThreadLocal��ʹ�ÿռ任ʱ�䣬synchronized��ʹ��ʱ�任�ռ�
 * ������hibernate��session�ʹ�����ThreadLocal�У�����synchronized��ʹ��
 * <p>
 * ��������ĳ������ThreadLocal
 *
 * @author ��ʿ��
 */
package com.weiyzue.juc.c_022_RefTypeAndThreadLocal;

import com.weiyzue.util.SleepHelper;

public class ThreadLocal2 {
    //volatile static Person p = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    //static List<Person> list = new ArrayList<>();

    static ThreadLocal<String> tl2 = new ThreadLocal<>();

    //tl2 = ne
    //static ThreadLocal<String> person = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {

            //list.add(new Person("xiaoyao"));

            SleepHelper.sleepSeconds(1);
            tl.set(new Person("zhangsan"));
            System.out.println(tl.get());
            tl.remove();
        }).start();

        new Thread(() -> {
            SleepHelper.sleepSeconds(2);

            System.out.println(tl.get());
        }).start();

    }

    static class Person {
        public Person(String name) {
            this.name = name;
        }

        String name = "zhangsan";
    }
}


