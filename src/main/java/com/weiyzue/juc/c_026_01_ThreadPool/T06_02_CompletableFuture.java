/**
 * 假设你能够提供一个服务
 * 这个服务查询各大电商网站同一类产品的价格并汇总展示
 *
 * @author 马士兵 http://mashibing.com
 * 测试一下thenAccept是在哪个线程中执行的
 */

package com.weiyzue.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class T06_02_CompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        CompletableFuture.supplyAsync(() -> priceOfTM())

                .thenAccept((result) -> after(result));


        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM() {
        System.out.println(Thread.currentThread().getName());
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        System.out.println(Thread.currentThread().getName());
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        System.out.println(Thread.currentThread().getName());
        delay();
        return 3.00;
    }

    /*private static double priceOfAmazon() {
        delay();
        throw new RuntimeException("product not exist!");
    }*/

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }

    private static void after(Double result) {
        System.out.println("result = " + result + " 结束于线程：" + Thread.currentThread().getName());
    }
}
