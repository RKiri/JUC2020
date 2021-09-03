package com.weiyzue.insidesync;

import org.openjdk.jol.info.ClassLayout;

public class T03_SyncHashCode {


    public static void main(String[] args) {
        Lock lock = new Lock();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        lock.hashCode();

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

    }
}
