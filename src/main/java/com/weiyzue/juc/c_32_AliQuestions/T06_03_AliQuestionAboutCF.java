package com.weiyzue.juc.c_32_AliQuestions;

import com.weiyzue.util.SleepHelper;

import java.util.concurrent.CompletableFuture;

/**
 * 问题：可以归为分布式事务失败回滚的手工实现
 * 代码依然不太完善，等待你的进一步完善
 */

public class T06_03_AliQuestionAboutCF {

    public static void main(String[] args) throws Exception {
        MyTask task1 = new MyTask("task1", 3, true);
        MyTask task2 = new MyTask("task2", 4, true);
        MyTask task3 = new MyTask("task3", 1, false);


        CompletableFuture f1 = CompletableFuture.supplyAsync(() -> task1.call())
                .thenAccept((result) -> callback(result));
        CompletableFuture f2 = CompletableFuture.supplyAsync(() -> task2.call())
                .thenAccept((result) -> callback(result));
        CompletableFuture f3 = CompletableFuture.supplyAsync(() -> task3.call())
                .thenAccept((result) -> callback(result));


        System.in.read();

    }

    private static void callback(Boolean result) {
        if (false == result) {
            //处理结束流程
            //通知其他线程结束（回滚）
            //超时处理
            System.exit(0); //太过暴力，只能给50分
        }
    }

    private static class MyTask {

        private String name;
        private int timeInSeconds;
        private boolean success;

        public MyTask(String name, int timeInSeconds, boolean success) {
            this.name = name;
            this.timeInSeconds = timeInSeconds;
            this.success = success;
        }


        public Boolean call() {
            //模拟业务执行时间
            //实际中时间不固定，可能在处理计算任务，或者是IO任务
            SleepHelper.sleepSeconds(timeInSeconds);

            System.out.println(name + " 任务结束！");
            return success;
        }
    }
}


