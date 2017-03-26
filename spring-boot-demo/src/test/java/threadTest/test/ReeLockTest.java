package threadTest.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.fastjson.JSON;

public class ReeLockTest {
	static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {

		final List<Integer> list = new ArrayList<Integer>();

		new Thread(new Runnable() {
			public void run() {
				add(list, 1);
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				add(list, 2);
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				add(list, 3);
			}
		}).start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(JSON.toJSONString(list));

	}

	public static List<Integer> add(List<Integer> list, int i) {
		lock.lock();
		try {
			list.add(i);
		} finally {
			lock.unlock();
		}

		return list;
	}

}
