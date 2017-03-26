package threadTest.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.tomcat.jni.Lock;

public class LockTest {

	private final ReentrantLock mainLock = new ReentrantLock();
	
//	volatile static List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) {

//		final List<Integer> list = new ArrayList<Integer>();
		
		final List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
		
		
		final CopyOnWriteArrayList<Integer> list2 = new CopyOnWriteArrayList<Integer>();
		
		final LockTest lockTest = new LockTest();

		new Thread(new Runnable() {

			@Override
			public void run() {
//				list.add(1);
				lockTest.add(list, 1);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
//				list.add(2);
				lockTest.add(list, 2);
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
//				list.add(3);
				lockTest.add(list, 3);
			}
		}).start();
		
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		for (Integer integer : list) {
			System.out.println(integer);
		}

	}

	public void add(List<Integer> list, Integer i) {

//		final ReentrantLock lock = this.mainLock;

//		lock.lock();

		try {
//			list.add(new Random().nextInt(100));
			list.add(i);
		} finally {
//			lock.unlock();
		}
	}

}
