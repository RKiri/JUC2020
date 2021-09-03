package com.weiyzue.juc.c_011_02_handleexceptions;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 线程异常不会被另外一个线程捕获
 */
public class T04_BasicHandle {
    public static void main(String[] args) {
        Runnable r = () -> {
            Thread t = Thread.currentThread();
            System.out.println("run by " + t);
            System.out.println("eh " + t.getUncaughtExceptionHandler());
            throw new RuntimeException();
        };

        try {
            Executors.newCachedThreadPool(new HandlerThreadFactory()).execute(r);
        } catch (RuntimeException e) {
            System.out.println("Exception!");
        }


    }

    public static class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("got u " + e);
        }
    }

    public static class HandlerThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            return t;
        }
    }
}
