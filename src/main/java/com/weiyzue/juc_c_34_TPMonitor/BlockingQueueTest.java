package com.weiyzue.juc_c_34_TPMonitor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

    @Test
    public void testPut() {
        BlockingQueue<String> q = new LinkedBlockingQueue<>(2);
        try {
            q.put("1");
            q.put("2");

            System.out.println(q);

            q.put("3");

            System.out.println(q);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testTake() {
        BlockingQueue<String> q = new LinkedBlockingQueue<>(2);
        try {
            q.put("1");
            q.put("2");

            System.out.println(q.take());
            System.out.println(q.take());

            System.out.println(q);

            System.out.println(q.take());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOfferAndPoll() {
        BlockingQueue<String> q = new LinkedBlockingQueue<>(2);
        boolean b1 = q.offer("1");
        boolean b2 = q.offer("2");

        boolean b3 = q.offer("3");
        System.out.println(b3 ? "插入成功" : "插入失败" + q);

        String s1 = q.poll();
        String s2 = q.poll();

        String s3 = q.poll();
        System.out.println(s1 + " | " + s2 + " | " + s3);

    }

    @Test
    public void testAdd() {
        BlockingQueue<String> q = new LinkedBlockingQueue<>(2);
        q.add("1");
        q.add("2");

        q.add("3");
    }


}
