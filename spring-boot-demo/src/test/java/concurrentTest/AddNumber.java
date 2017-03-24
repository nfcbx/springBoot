package concurrentTest;

public class AddNumber implements Runnable {
	
	private Integer number;
	
	public AddNumber(Integer num) {
		number = num;
	}

	@Override
	public void run() {
		
		number++;

	}

}
