package threadTest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilTest {
	
	public static void main(String[] args) {
		
		
		
//		获取系统处理器个数，作为线程池数量
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(availableProcessors);
		
		/*
		 * 10  20ren   max  30  100ren wait  
		 */
		
		ExecutorService executorService = Executors.newFixedThreadPool(availableProcessors);
		
		
		for (int i = 0; i < 5; i++) {
//			executorService.execute(new Task1(i));
			
			executorService.execute(new Task2());
			
//			Task2 task2 = new Task2();
//			task2.run();
		}
		
		System.out.println("准备关闭线程池");
		executorService.shutdown();
		
//		executorService.execute(new Runnable() {
//			
//			@Override
//			public void run() {
//				
//				while (true) {
//					System.out.println(Calendar.getInstance().getTime());
//					try {
//						Thread.sleep(1000);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
		
	}

}
