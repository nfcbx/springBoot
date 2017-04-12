package ioc;

public class TTTest {

	public static void main(String[] args) {
		
		
//		Object newInstance = newInstance("java.lang.String");
		Object newInstance = newInstance("ioc.Cat");
		
		System.out.println(newInstance.getClass());
		
		

	}

	public static Object newInstance(String className) {

		Class<?> cls = null;
		Object obj = null;

		try {
			cls = Class.forName(className);
			

			obj = cls.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return obj;

	}

}
