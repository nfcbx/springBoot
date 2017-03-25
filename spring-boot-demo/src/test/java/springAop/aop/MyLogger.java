package springAop.aop;

import java.lang.reflect.Method;

public class MyLogger implements ILogger {

	@Override
	public void start(Method method) {
		System.out.println(method.getName() + " 开始了");

	}

	@Override
	public void end(Method method) {
		System.out.println(method.getName() + " 结束了");

	}

}
