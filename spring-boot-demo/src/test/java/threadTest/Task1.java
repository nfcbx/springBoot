package threadTest;

import java.util.Random;

public class Task1 implements Runnable {
	
	private int num;

	public Task1(int i) {
		num = i;
	}

	@Override
	public void run() {
		
		Random random = new Random();
		
		int nextInt = random.nextInt(60);
		
//		nextInt = num;
		
		try {
			Thread.sleep(nextInt);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

		System.out.println(num + " -- " + nextInt);
	}

}
