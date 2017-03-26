package threadTest.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TTT {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newCachedThreadPool();

		// executorService.execute(command);

		// corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
		
		threadPoolExecutor.execute(new Runnable() {
			
			@Override
			public synchronized void run() {
				try {
					wait(1000 * 5L); // wait 和  notify 必须 有 synchronized
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("444444");
				
			}
		});
		
		
		
		while(threadPoolExecutor.getActiveCount() != 0){
			
		}
		
		threadPoolExecutor.shutdown();

	}

	// public void execute(Runnable command) {
	// if (command == null)
	// throw new NullPointerException();
	// if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
	// if (runState == RUNNING && workQueue.offer(command)) {
	// if (runState != RUNNING || poolSize == 0)
	// ensureQueuedTaskHandled(command);
	// } else if (!addIfUnderMaximumPoolSize(command))
	// reject(command); // is shutdown or saturated
	// }
	// }
	//
	// private boolean addIfUnderCorePoolSize(Runnable firstTask) {
	// Thread t = null;
	// final ReentrantLock mainLock = this.mainLock;
	// mainLock.lock();
	// try {
	// if (poolSize < corePoolSize && runState == RUNNING)
	// t = addThread(firstTask);
	// } finally {
	// mainLock.unlock();
	// }
	// if (t == null)
	// return false;
	// t.start();
	// return true;
	// }
	//
	// private boolean addIfUnderMaximumPoolSize(Runnable firstTask) {
	// Thread t = null;
	// final ReentrantLock mainLock = this.mainLock;
	// mainLock.lock();
	// try {
	// if (poolSize < maximumPoolSize && runState == RUNNING)
	// t = addThread(firstTask);
	// } finally {
	// mainLock.unlock();
	// }
	// if (t == null)
	// return false;
	// t.start();
	// return true;
	// }
}
