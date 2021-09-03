/**
 * ��N�Ż�Ʊ��ÿ��Ʊ����һ�����
 * ͬʱ��10�����ڶ�����Ʊ
 * ��дһ��ģ�����
 * <p>
 * ��������ĳ�����ܻ������Щ���⣿
 * �ظ����ۣ��������ۣ�
 * <p>
 * ʹ��Vector����Collections.synchronizedXXX
 * ����һ�£������ܽ��������
 * <p>
 * �������A��B����ͬ���ģ���A��B��ɵĸ��ϲ���Ҳδ����ͬ���ģ���Ȼ��Ҫ�Լ�����ͬ��
 * ������������ж�size�ͽ���remove������һ������ԭ�Ӳ���
 * <p>
 * ʹ��ConcurrentQueue��߲�����
 *
 * @author ��ʿ��
 */
package com.weiyzue.juc.c_024_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();


    static {
        for (int i = 0; i < 1000; i++) tickets.add("Ʊ ��ţ�" + i);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) break;
                    else System.out.println("������--" + s);
                }
            }).start();
        }
    }
}
