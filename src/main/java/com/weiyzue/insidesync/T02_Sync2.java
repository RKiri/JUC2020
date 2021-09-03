package com.weiyzue.insidesync;

import org.openjdk.jol.info.ClassLayout;

public class T02_Sync2 {

    public static void main(String[] args) throws Exception {
        Thread.sleep(5000);

        Lock lock = new Lock();

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock) {
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }

        //Vector

        //System.out.println(ClassLayout.parseInstance(lock).toPrintable());


    }
}
