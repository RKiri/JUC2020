package com.weiyzue.juc.c_022_RefTypeAndThreadLocal;

import java.io.IOException;

public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC
        System.out.println(m);

        System.in.read();//阻塞main线程，给垃圾回收线程时间执行
    }
}
