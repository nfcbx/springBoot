package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
	
//	static List<Integer> list = new ArrayList<Integer>();
	
	static List<Integer> list = new CopyOnWriteArrayList<Integer>();

	public static void main(String[] args) throws Exception {

		

		new Thread(new Runnable() {

			public void run() {
				list.add(1);
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				list.add(2);
			}
		}).start();
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				list.add(3);
			}
		});
		
		thread.start();
		
		System.out.println();
//		Thread.sleep(1 * 1000);
		for (Integer integer : list) {
			System.out.println(integer);
		}

	}

}


class listThread implements Runnable {

	public void run() {
	}
	
}