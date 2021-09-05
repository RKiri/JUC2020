package com.weiyzue.juc.c_001_03_Ordering;

public class T02_NoVisibility {
    private static boolean ready = false;//可见性 加volatile
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();//同步刷新 ready = true 造成可见
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t = new ReaderThread();
        t.start();
        number = 42;//两者没有前后依赖关系 可能会换顺序
        ready = true;//并不会马上停止 MESI的主动性
        t.join();
    }
}
