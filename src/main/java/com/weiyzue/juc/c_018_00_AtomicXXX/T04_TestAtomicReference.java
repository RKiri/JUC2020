package com.weiyzue.juc.c_018_00_AtomicXXX;

import com.weiyzue.util.SleepHelper;

public class T04_TestAtomicReference {

    private static class Student {
        String name;
        int age;

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static Student chairman;

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(() -> {
                chairman = new Student();
                chairman.name = "s-" + j;
                SleepHelper.sleepMilli(1);
                chairman.age = j;
            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(chairman);
    }
}
