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
 *
 * @author ��ʿ��
 */
package com.weiyzue.juc.c_024_FromVectorToQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketSeller3 {
    static List<String> tickets = new LinkedList<>();


    static {
        for (int i = 0; i < 1000; i++) tickets.add("Ʊ ��ţ�" + i);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0) break;

                        try {
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("������--" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
