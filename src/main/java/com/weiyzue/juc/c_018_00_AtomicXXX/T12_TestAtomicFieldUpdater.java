package com.weiyzue.juc.c_018_00_AtomicXXX;

import com.weiyzue.util.SleepHelper;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class T12_TestAtomicFieldUpdater {

    @Test
    public void m() {
        AtomicBoolean ab = new AtomicBoolean(false);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                boolean b = ab.compareAndSet(false, true);
                System.out.println(b ? "-----成功" : "失败");
            }).start();
        }

        SleepHelper.sleepSeconds(3);

        System.out.println();
    }
}
