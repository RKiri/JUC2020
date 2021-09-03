package com.weiyzue.juc.c_32_AliQuestions;

import com.weiyzue.util.SleepHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 最原始的方法，Thread run()重写
 */

public class T00_F3 {

    private static class Boss extends Thread {
        private List<Worker> tasks = new ArrayList<>();

        public void addTask(Worker t) {
            tasks.add(t);
        }

        @Override
        public void run() {
            tasks.stream().forEach((t) -> t.start());
        }

        public void end(Worker worker) {
            if (worker.getResult() == Result.FAILED) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Boss boss = new Boss();
        Worker t1 = new Worker(boss, "t1", 3, true);
        Worker t2 = new Worker(boss, "t2", 4, true);
        Worker t3 = new Worker(boss, "t3", 1, false);

        boss.addTask(t1);
        boss.addTask(t2);
        boss.addTask(t3);

        //启动线程

        boss.start();

        System.in.read();
    }

    private static enum Result {
        NOTEND, SUCCESSED, FAILED
    }

    private static class Worker extends Thread {

        private Result result = Result.NOTEND;

        private Boss boss;
        private String name;
        private int timeInSeconds;
        private boolean success;

        public Worker(Boss boss, String name, int timeInSeconds, boolean success) {
            this.boss = boss;
            this.name = name;
            this.timeInSeconds = timeInSeconds;
            this.success = success;
        }

        public Result getResult() {
            return result;
        }

        @Override
        public void run() {
            //模拟业务执行时间
            //实际中时间不固定，可能在处理计算任务，或者是IO任务
            SleepHelper.sleepSeconds(timeInSeconds);

            System.out.println(name + " 任务结束！");

            result = success ? Result.SUCCESSED : Result.FAILED;

            boss.end(this);
        }


    }


}
