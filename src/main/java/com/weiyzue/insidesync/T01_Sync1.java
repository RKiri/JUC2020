package com.weiyzue.insidesync;

import org.openjdk.jol.info.ClassLayout;

import java.util.Hashtable;

public class T01_Sync1 {
    Hashtable h = new Hashtable();


    private static class T {
//        int m;
//        String s;
    }


    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(5000);

        T t = new T();
        //工具里面的类 分析 转换可打印字符串
        System.out.println(ClassLayout.parseInstance(t).toPrintable());

        /*OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        0 4 从0字节开始往后数4个字节 (object header)                    05 00 00 00 (00000101 00000000 00000000 00000000) (5)//看后三位 101
             4       4        (object header) 每行4个 共8个字节         00 00 00 00 (00000000 00000000 00000000 00000000) (0)
             8       4        (object header)                           08 01 24 00 (00001000 00000001 00100100 00000000) (2359560) //值指向 通过他找到.class
            12       4        (loss due to the next object alignment) 对象的对齐
         Instance size: 16 bytes 整个大小 */

        //t.hashCode();
        synchronized (t) {//锁定这个对象 共享资源 锁定他
            System.out.println(ClassLayout.parseInstance(t).toPrintable());//锁定后布局输出
        }

        /*OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        0     4        (object header)                           05 a0 09 b4 (00000101 10100000 00001001 10110100) (-1274437627)//随着synchronized越来越复杂
        4     4        (object header)                           67 02 00 00 (01100111 00000010 00000000 00000000) (615)//前两行不一样 已经偏向main线程 真正的偏向锁
        8     4        (object header)                           08 01 24 00 (00001000 00000001 00100100 00000000) (2359560)
        12     4        (loss due to the next object alignment)
        Instance size: 16 bytes*/
    }
}
