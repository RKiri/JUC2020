package com.weiyzue.insidesync;

import org.openjdk.jol.info.ClassLayout;

import java.util.Hashtable;

public class T01_Sync1 {
    Hashtable h = new Hashtable();


    private static class T {
        int m;
        String s;
    }


    public static void main(String[] args) throws InterruptedException {

        //Thread.sleep(5000);

        T t = new T();
        System.out.println(ClassLayout.parseInstance(t).toPrintable());

        //t.hashCode();
        synchronized (t) {
            System.out.println(ClassLayout.parseInstance(t).toPrintable());
        }

    }
}
