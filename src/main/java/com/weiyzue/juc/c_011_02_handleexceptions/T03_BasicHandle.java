package com.weiyzue.juc.c_011_02_handleexceptions;

/**
 * 线程异常不会被另外一个线程捕获
 */
public class T03_BasicHandle {
    public static void main(String[] args) {
        Runnable r = () -> {
            Thread t = Thread.currentThread();
            System.out.println("run by " + t);
            System.out.println("eh " + t.getUncaughtExceptionHandler());
            throw new RuntimeException();
        };

        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler()); //设置所有线程的默认异常处理器
        t.start();
    }

    public static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("got u " + e);
        }
    }

}
