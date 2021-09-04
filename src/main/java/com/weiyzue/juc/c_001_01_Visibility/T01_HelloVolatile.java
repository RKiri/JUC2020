/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 * <p>
 * 在下面的代码中，running是存在于堆内存的t对象中
 * 当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去
 * 读取堆内存，这样，当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
 * <p>
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 *
 * @author weiyzue
 */
package com.weiyzue.juc.c_001_01_Visibility;

import com.weiyzue.util.SleepHelper;

import java.io.IOException;

public class T01_HelloVolatile {
    private static /*volatile*/ boolean running = true;

    private static void m() {
        System.out.println("m start");
        while (running) {
            System.out.println("hello");
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) throws IOException {

        new Thread(T01_HelloVolatile::m, "t1").start();//执行m方法

        SleepHelper.sleepSeconds(1);

        running = false;

        //System.in.read();
    }
}


