package com.weiyzue.juc.c_018_01_Unsafe;

//import sun.misc.*;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class HelloUnsafe {
    static class M {
        private M() {
        }

        int i = 0;
    }

    public static void main(String[] args) throws InstantiationException {

        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M) unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }

    @Test
    public void m1() throws Exception {

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            unsafe.allocateInstance(M.class);
        }
        long end = System.currentTimeMillis();
        System.out.println("unsafe :" + (end - start));
    }

    @Test
    public void m2() throws Exception {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000_0000; i++) {
            new M();
        }
        long end = System.currentTimeMillis();
        System.out.println("new :" + (end - start));
    }
}


