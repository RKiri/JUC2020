package com.weiyzue.insidesync;

public class T {
    private static Object o = new Object();

    public static void main(String[] args) {
        synchronized (o) {
            System.out.println("Hello!");
        }
    }


}
