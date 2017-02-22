package threadTest;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilTest {
	
	public static void main(String[] args) {
//		获取系统处理器个数，作为线程池数量
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		System.out.println(availableProcessors);
		
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		
		
		for (int i = 0; i < 100; i++) {
//			System.out.println(i);
			executorService.execute(new Task1(i));
		}
		
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
