package com.weiyzue.juc.c_30_somequestions_from_students;

import java.util.Random;

/**
 * 模拟一场跑步比赛，100米，100个人
 */

public class Running {
    private static final double LAP_LENGTH = 100.0;

    private static class Runner extends Thread {
        private double speed = new Random().nextDouble() * 5 + 5.0;

        public Runner(String name) {
            this.setName(name);
            System.out.println(name + " 准备好了 速度 = " + speed);
        }

        @Override
        public void run() {

        }
    }
}
