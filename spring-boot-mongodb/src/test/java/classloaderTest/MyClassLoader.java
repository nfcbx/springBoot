package classloaderTest;

public class MyClassLoader extends ClassLoader {
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		
		System.out.println("name : " + name);
		
		
		return super.findClass(name);
	}

}
