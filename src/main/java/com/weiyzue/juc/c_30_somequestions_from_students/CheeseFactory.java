package com.weiyzue.juc.c_30_somequestions_from_students;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 题目：有一个生产奶酪的厂家，每天需要生产100000份奶酪卖给超市，通过一辆货车发货，送货车每次送100份
 * 厂家有一个容量为1000份的冷库，用于奶酪保鲜，生产的奶酪需要先存放在冷库，运输车辆从冷库取货
 * 厂家有三条生产线，分别是牛奶供应生产线，发酵剂制作生产线，奶酪生产线。
 * 生产每份奶酪需要2份牛奶和一份发酵剂
 * 请设计生产系统
 */
public class CheeseFactory {
    private static class Cheese {
    }

    private static class Milk {
    }

    private static class Ferment {
    }

    //冷库
    BlockingQueue<Cheese> codeStorage = new ArrayBlockingQueue<>(1000);

    //原料存储
    BlockingQueue<Milk> milks = new ArrayBlockingQueue<>(2000);
    BlockingQueue<Ferment> ferments = new ArrayBlockingQueue<>(1000);


    //消费者：货车
    //

    //CompletableFuture?
    //AllOf?
}
