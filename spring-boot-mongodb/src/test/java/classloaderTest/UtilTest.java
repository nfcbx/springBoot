package classloaderTest;

public class UtilTest {

	public static void main(String[] args) throws Exception {
		
		ClassLoader loader = new MyClassLoader();
		
//		ClassLoader loader = ClassLoader.getSystemClassLoader();
		
		Thread.currentThread().setContextClassLoader(new MyClassLoader());
		
		Class<?> loadClass = loader.loadClass("classloaderTest.UtilTest");
		
		System.out.println(loadClass.getName());
		
//		Method method = loadClass.getMethod("main", String[].class);
		
		
		
//		method.invoke(loadClass.newInstance(), args);
		
		
		
		
		
		
		
	}

}



/**
 * 
 * 
 * 
 * Class的装载分了三个阶段，loading，linking和initializing，分别定义在The Java Language Specification的12.2，12.3和12.4。
Class.forName(className)
实际上是调用Class.forName(className, true,
this.getClass().getClassLoader())。注意第二个参数，是指Class被loading后是不是必须被初始化。
ClassLoader.loadClass(className)实际上调用的是ClassLoader.loadClass(name, false)，第二个参数指出Class是否被link。
区别就出来了。Class.forName(className)装载的class已经被初始化，而ClassLoader.loadClass(className)装载的class还没有被link。
一般情况下，这两个方法效果一样，都能装载Class。但如果程序依赖于Class是否被初始化，就必须用Class.forName(name)了。
例
如，在JDBC编程中，常看到这样的用法，Class.forName("com.mysql.jdbc.Driver")，如果换成了
getClass().getClassLoader().loadClass("com.mysql.jdbc.Driver")，就不行。
 * 
 * 
 * 
 */
