package threadTest;

import java.util.Date;

public class Task2 implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(1000 * 5);
			System.out.println(new Date().toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
	}

	

}
