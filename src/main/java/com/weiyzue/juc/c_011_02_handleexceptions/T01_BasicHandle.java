package com.weiyzue.juc.c_011_02_handleexceptions;

/**
 * 线程异常不会被另外一个线程捕获
 */
public class T01_BasicHandle {
    public static void main(String[] args) {
        Runnable r = () -> {
            throw new RuntimeException();
        };

        try {
            new Thread(r).start();
        } catch (RuntimeException e) {
            System.out.println("Exception!");
        }
    }
}
