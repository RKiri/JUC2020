package com.weiyzue.juc.c_020_juclocks;

import com.weiyzue.util.SleepHelper;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestReadWriteLock {
    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        lock.lock();
        try {
            SleepHelper.sleepSeconds(1);
            System.out.println("read over!");
            //模拟读取操作
        } finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        lock.lock();
        try {
            SleepHelper.sleepSeconds(1);
            value = v;
            System.out.println("write over!");
            //模拟写操作
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Runnable readR = () -> read(lock);
        //Runnable readR = ()-> read(readLock);

        Runnable writeR = () -> write(lock, new Random().nextInt());
        //Runnable writeR = ()->write(writeLock, new Random().nextInt());

        for (int i = 0; i < 18; i++) new Thread(readR).start();
        for (int i = 0; i < 2; i++) new Thread(writeR).start();


    }
}
