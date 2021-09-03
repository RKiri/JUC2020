package com.weiyzue.juc.c_000_threadbasic;

public class T12_Final_01 {
    public final static int z; //最好在声明的时候就进行初始化

    static {
        z = 9;
    }

    final int x;

    public T12_Final_01() {
        x = 9;
    }

    public void m(final int y) {
        //y = 3;
        //x = 10;
    }
}
