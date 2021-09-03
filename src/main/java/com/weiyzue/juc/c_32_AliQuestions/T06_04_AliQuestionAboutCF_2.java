package com.weiyzue.juc.c_32_AliQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 问题：可以归为分布式事务失败回滚的手工实现
 * 代码依然不太完善，等待你的进一步完善
 */

public class T06_04_AliQuestionAboutCF_2 {
    //任务执行结束的三种状态
    private static enum Result {
        SUCCESS, FAIL, CANCELLED
    }

    static List<MyTask> tasks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        MyTask task1 = new MyTask("task1", 3, Result.SUCCESS);
        MyTask task2 = new MyTask("task2", 4, Result.SUCCESS);
        MyTask task3 = new MyTask("task3", 1, Result.FAIL);


        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        for (MyTask task : tasks) {

            CompletableFuture f = CompletableFuture.supplyAsync(() -> task.runTask())
                    .thenAccept((result) -> callback(result, task));
        }

        System.in.read();

    }

    private static void callback(Result result, MyTask task) {
        //可以处理的更加精确一些，根据不同的任务状态，做出到底是取消还是忽略这样的处理 2PC
        //这里要考虑同步，除非cancel无状态，幂等，否则应该加同步
        if (Result.FAIL == result) {
            for (MyTask _task : tasks) {
                if (_task != task) {
                    _task.cancel(); //加超时处理，加出错处理，设计任务接口
                }
            }
            //处理结束流程
            //通知其他线程结束（回滚）
            //超时处理

        }
    }

    private static class MyTask {

        private String name;
        private int timeInSeconds;
        private Result ret;

        volatile boolean cancelling = false;
        volatile boolean cancelled = false;

        public MyTask(String name, int timeInSeconds, Result ret) {
            this.name = name;
            this.timeInSeconds = timeInSeconds * 1000;
            this.ret = ret;
        }


        public Result runTask() {
            int interval = 100;
            int total = 0;

            try {
                for (; ; ) {
                    Thread.sleep(interval); //cpu密集型
                    total += interval;
                    if (total >= timeInSeconds) break;
                    if (cancelled) return Result.CANCELLED;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " end!");

            return ret;
        }

        public void cancel() {
            if (!cancelled) {
                synchronized (this) {
                    if (cancelled) return;
                    cancelling = true; //正在cancel
                    System.out.println(name + "cancelling");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace(); //记录日志 通知主线程 cancel不掉，考虑干掉
                    }
                    System.out.println(name + "cancelled");
                    cancelled = true;
                    cancelling = false;
                }
            }

        }
    }
}


