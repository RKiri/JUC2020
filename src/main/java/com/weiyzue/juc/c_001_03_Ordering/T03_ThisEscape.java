package com.weiyzue.juc.c_001_03_Ordering;

public class T03_ThisEscape {

    private int num = 8;


    public T03_ThisEscape() {
        new Thread(() -> System.out.println(this.num)
        ).start();
    }

    public static void main(String[] args) throws Exception {
        new T03_ThisEscape();
        System.in.read();//确认主程序结束之前 加一个阻塞 确保线程执行完
    }
}
