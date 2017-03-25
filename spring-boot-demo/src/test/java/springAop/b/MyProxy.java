package springAop.b;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import springAop.a.Logger;

public class MyProxy implements InvocationHandler {

	private Object target;

	public Object bind(Object object) {
		this.target = object;

		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		Logger.before();//添加额外的方法
		
		
		//通过反射机制来运行目标对象的方法
		result = method.invoke(this.target, args);

		Logger.after();

		return result;
	}

}
