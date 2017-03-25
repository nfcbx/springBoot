package springAop;

import springAop.a.AAA;
import springAop.a.ProxyAAA;
import java.lang.reflect.InvocationHandler;

public class UnitTest {

	public static void main(String[] args) {
		AAA aaa = new AAA();

//		aaa.test();
		
		ProxyAAA proxyAAA = new ProxyAAA(aaa);
		
		proxyAAA.test();
		
		
	}

}
