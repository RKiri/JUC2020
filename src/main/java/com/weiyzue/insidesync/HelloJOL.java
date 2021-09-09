package com.weiyzue.insidesync;

import org.openjdk.jol.info.ClassLayout;

public class HelloJOL {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
