package com.weiyzue.juc.c_001_03_Ordering;

public class T03_ThisEscape {

    private int num = 8;


    public T03_ThisEscape() {
        new Thread(() -> System.out.println(this.num)
        ).start();
    }

    public static void main(String[] args) throws Exception {
        new T03_ThisEscape();
        System.in.read();
    }
}
