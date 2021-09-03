package com.weiyzue.juc.c_018_00_AtomicXXX;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.atomic.AtomicReference;

public class T06_TestAtomicReference {

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

    static AtomicReference<Student> chairman = new AtomicReference<>();

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            final int j = i;
            new Thread(() -> {

                Student s = new Student();
                s.name = "s-" + j;
                SleepHelper.sleepMilli(1);
                s.age = j;

                chairman.set(s);

            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println(chairman.get());
    }
}
