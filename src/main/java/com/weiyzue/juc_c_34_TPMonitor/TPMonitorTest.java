package com.weiyzue.juc_c_34_TPMonitor;

import com.weiyzue.util.SleepHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TPMonitorTest {

    @Test
    public void learnMonitorAPI() throws IOException {
        ThreadPoolExecutor e = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
        e.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 20; i++) {
            final int j = i;
            e.submit(() -> {
                SleepHelper.sleepSeconds(new Random().nextInt(5));
                System.out.println(j + "end!");
            });
        }

        new Thread(() -> {
            while (true) {
                System.out.println("任务数: " + e.getTaskCount());
                System.out.println("任务数完成数: " + e.getCompletedTaskCount());
                System.out.println("core pool size: " + e.getCorePoolSize());
                System.out.println("活跃线程数： " + e.getActiveCount());

                ThreadGroup parentThread;
                int totalThread = 0;
                for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                        .getParent() != null; parentThread = parentThread.getParent()) {
                    totalThread = parentThread.activeCount();
                }
                System.out.println("获得线程总数:" + totalThread);

                System.out.println("Pool Size: " + e.getPoolSize());

                System.out.println();
                SleepHelper.sleepSeconds(1);

            }
        }).start();

        System.in.read();
    }

    public static void main(String[] args) {
        ThreadGroup parentThread;
        int totalThread = 0;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent()) {
            totalThread += parentThread.activeCount();
        }

        Thread[] threads = new Thread[100];
        parentThread.enumerate(threads);

        for (Thread thread : threads) {
            System.out.println(thread != null ? thread.getName() : " ");
        }

        System.out.println("获得线程总数:" + totalThread);
    }
}
