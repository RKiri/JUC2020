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

        //将来会产生返回值的任务;implement RunnableFuture;接口实现了run()
        //extends Runnable、Future，既能扔给Thread运行，得到的结果、返回值也可以装在自己里面
        //可传Callable
        FutureTask<String> task = new FutureTask<>(new MyCall());//传的对象要带返回值，传Callable
        Thread t = new Thread(task);//不能直接传Callable 只能传Runnable
        t.start();
        System.out.println(task.get());

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("Hello ThreadPool");
        });

        //Future 异步 扔给线程池线程一个任务 任务执行完返回值给Future 不需要等待
        //将来有可能拿到的值
        Future<String> f = service.submit(new MyCall());//可添加Callable、Runnable
        String s = f.get();//拿到值 阻塞类型方法
        System.out.println(s);
        service.shutdown();

    }

}

