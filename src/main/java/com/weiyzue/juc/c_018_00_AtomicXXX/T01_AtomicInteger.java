/**
 * 解决同样的问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 *
 * @author weiyzue
 */
package com.weiyzue.juc.c_018_00_AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class T01_AtomicInteger {
	/*volatile*/ //int count1 = 0;

	AtomicInteger count = new AtomicInteger(0);//初始值为0 原子的Int类型

	/* synchronized */void m() {
		for (int i = 0; i < 10000; i++)
			//if count1.get() < 1000
			//增加操作自带原子性 增加拿新值 并没有拿新值 只看增加
			//调用Unsafe类的getAndAddInt() do while不停循环
		    //compareAndSetInt() native hotspot C C++实现 Java代码到此为止
			count.incrementAndGet(); //count1++
	}

	public static void main(String[] args) {
		T01_AtomicInteger t = new T01_AtomicInteger();

		List<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i < 100; i++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}

		threads.forEach((o) -> o.start());

		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println(t.count);

	}

}
