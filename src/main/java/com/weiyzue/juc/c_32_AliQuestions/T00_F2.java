package com.weiyzue.juc.c_32_AliQuestions;

import com.weiyzue.util.SleepHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 最原始的方法，Thread run()重写
 */

public class T00_F2 {

    public static void main(String[] args) throws Exception {
        MyTask t1 = new MyTask("t1", 3, true);
        MyTask t2 = new MyTask("t2", 4, true);
        MyTask t3 = new MyTask("t3", 1, false);

        List<MyTask> tasks = new ArrayList<>();

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        //启动线程
        tasks.stream().forEach((t) -> t.start());

        //启动监视
        for (; ; ) {
            for (MyTask task : tasks) {
                if (task.getResult() == Result.FAILED) {
                    System.exit(0);
                }
            }
        }
    }

    private static enum Result {
        NOTEND, SUCCESSED, FAILED
    }

    private static class MyTask extends Thread {

        private Result result = Result.NOTEND;
        private String name;
        private int timeInSeconds;
        private boolean success;

        public MyTask(String name, int timeInSeconds, boolean success) {
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
        }

    }
}
