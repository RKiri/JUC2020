package com.weiyzue.juc.c_022_RefTypeAndThreadLocal;

import java.nio.ByteBuffer;
import java.util.WeakHashMap;

public class TestWeakHashMap {
    public static void main(String[] args) {
        WeakHashMap<String, String> map = new WeakHashMap<>();

        ByteBuffer b = ByteBuffer.allocate(1024);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
    }
}
