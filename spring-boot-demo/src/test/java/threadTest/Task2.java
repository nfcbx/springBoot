package threadTest;

import java.util.Date;

public class Task2 implements Runnable {
	
	private Integer num = 1;

	@Override
	public void run() {
		try {
			Thread.sleep(100);
//			Thread.yield();
			
			System.out.println(++num);
			
			System.out.println(new Date().toString());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		
	}

	

}
