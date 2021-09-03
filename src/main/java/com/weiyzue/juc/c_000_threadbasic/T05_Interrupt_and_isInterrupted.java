package com.weiyzue.juc.c_000_threadbasic;

import com.weiyzue.util.SleepHelper;

/**
 * interrupt()与isInterrupted()
 * 设置标志位 + 查询标志位
 */
public class T05_Interrupt_and_isInterrupted {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (; ; ) {//死循环
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread is interrupted!");
                    System.out.println(Thread.currentThread().isInterrupted());
                    //比较优雅线程结束方案：
                    //隔一段时间 每一次循环 检查是否有人设置标志位，如果有，结束
                    break;
                }
            }
        });

        t.start();

        SleepHelper.sleepSeconds(2);

        t.interrupt();
    }
}
