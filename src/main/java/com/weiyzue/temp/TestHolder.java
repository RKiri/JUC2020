package com.weiyzue.temp;

public class TestHolder {
    Holder h;

    public void init() {
        h = new Holder(42);
    }

    public static void main(String[] args) {
        TestHolder th = new TestHolder();

        Thread[] threads = new Thread[10000];
        for (int i = 0; i < threads.length; i++) {
            for (int j = 0; j < 10000_0000; j++) {
                threads[i] = new Thread(() -> {
                    th.init();
                    th.h.m();
                });
            }
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}

class Holder {
    int n;

    public Holder(int n) {
        this.n = n;
    }

    public void m() {
        if (n != n) {
            System.out.println("Error");
        }
    }
}
