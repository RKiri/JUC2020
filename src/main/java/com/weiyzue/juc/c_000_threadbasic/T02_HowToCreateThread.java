package com.weiyzue.juc.c_000_threadbasic;

import java.util.concurrent.*;

public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    static class MyCall implements Callable<String> {
        @Override
        public String call() {
            System.out.println("Hello MyCall");
            return "success";
        }
    }

    //启动线程的5种方式
    public static void main(String[] args) throws Exception {
        new MyThread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> {
            System.out.println("Hello Lambda!");
        }).start();

        FutureTask<String> task = new FutureTask<>(new MyCall());
        Thread t = new Thread(task);
        t.start();
        System.out.println(task.get());

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("Hello ThreadPool");
        });

        Future<String> f = service.submit(new MyCall());
        String s = f.get();
        System.out.println(s);
        service.shutdown();

    }

}

