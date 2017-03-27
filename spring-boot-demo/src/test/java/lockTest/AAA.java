package lockTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class AAA {

	ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {

		final AAA aaa = new AAA();
		final ArrayList<Integer> list = new ArrayList<Integer>();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 2; i++) {
					aaa.add(list, 1);
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 2; i++) {
					aaa.add(list, 2);
				}
			}
		}).start();

		try {
			Thread.sleep(1000 * 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<Integer> list2  = new ArrayList<Integer>();
		
		while(list2.size() < 4){
			list2  = new ArrayList<Integer>();
			list2  = list;
			for (Integer integer : list2) {
				System.out.print(integer);
				System.out.println("---");
			}
		}
		
		

	}

	private boolean add(List<Integer> list, int num) {
		boolean add = false;
		lock.lock();
		try {
			try {
				Thread.sleep(1000 * 3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			add = list.add(num);
		} finally {
			lock.unlock();
		}
		return add;
	}

}
