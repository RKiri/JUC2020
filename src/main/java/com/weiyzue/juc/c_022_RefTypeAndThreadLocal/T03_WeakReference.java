/**
 * 弱引用遭到gc就会回收
 */
package com.weiyzue.juc.c_022_RefTypeAndThreadLocal;

import com.weiyzue.util.SleepHelper;

import java.lang.ref.WeakReference;

public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        SleepHelper.sleepSeconds(1);
        System.out.println(m.get());


        /*ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();*/

    }
}

