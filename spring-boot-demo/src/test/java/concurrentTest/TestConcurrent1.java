package concurrentTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrent1 {

	public static void main1(String[] args) throws InterruptedException {
		
		BlockingQueue queue = new ArrayBlockingQueue(1024);
		
		queue.put("aaaaaaa");
		queue.put("bbbbbb");
		queue.put("ccc");
		
		System.out.println(queue.size());
		
		Object take = queue.take();
		System.out.println(take);
		System.out.println(queue.size());
		
		take = queue.take();
		System.out.println(take);
		System.out.println(queue.size());
		
		take = queue.take();
		System.out.println(take);
		System.out.println(queue.size());
		
		take = queue.take(); // 在这里阻塞，即程序一直在运行中，显示红色， 不像取完后程序自定结束且变灰
		System.out.println(take);
		System.out.println(queue.size());
	}

	
	private Integer number;
	
	public static void main(String[] args) {
		
		
		
		
		AtomicInteger atomicInteger = new AtomicInteger(0);
		int incrementAndGet = atomicInteger.incrementAndGet();
		System.out.println(incrementAndGet); // 1
		int andIncrement = atomicInteger.getAndIncrement();
		System.out.println(andIncrement); // 1
		System.out.println(atomicInteger.get());  // 2
		
		
		
	}
	
	class numberAdd implements Runnable {
		public void run() {
			number ++;
		}
	}
	
	class numberAdd2 extends Thread {
		@Override
		public void run() {
			number ++;
		}
	}
}
