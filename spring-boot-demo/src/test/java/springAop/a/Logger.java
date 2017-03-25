package springAop.a;

public class Logger {

	public static void before() {
		System.out.println("开始前");
	}

	public static void after() {
		System.out.println("完成后");
	}
}
