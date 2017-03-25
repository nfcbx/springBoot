package springAop;

import java.lang.reflect.Method;

import springAop.a.AAA;
import springAop.a.ProxyAAA;
import springAop.aop.ILogger;
import springAop.aop.MyLogger;
import springAop.aop.TestProxy;
import springAop.b.BBB;
import springAop.b.BBBImpl;
import springAop.b.MyProxy;


public class UnitTest {

	public static void main(String[] args) {
		AAA aaa = new AAA();

//		aaa.test();
		
//		ProxyAAA proxyAAA = new ProxyAAA(aaa);
//		
//		proxyAAA.test();
		
		BBB bbb = (BBB) new MyProxy().bind(new BBBImpl());
		
		bbb.name("数学");
		
		
		BBB bind = (BBB) new TestProxy().bind(new BBBImpl(), new MyLogger());
		
		bind.name("小黄人");
	}

}

/**
我在学习 Java 的动态代理的时候，一直在使用 Proxy.newProxyInstance 方法生成代理的时候报错（如标题）。

我使用各种方法都无法解决这个问题，后来我直接用别人博客上的代码跑了一下，结果成功了。

于是我对比两个代码之间的差别，发现问题可能处在委托对象上面。

我写的代码委托对象是继承一个虚基类，而不是一个接口，于是我把我的代码改了一下，于是乎就跑起来了。。。

总结：要使用动态代理，用来生成代理的委托对象必须是一个接口。

*/