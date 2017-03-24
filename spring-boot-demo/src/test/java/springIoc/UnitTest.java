package springIoc;

import springIoc.config.BeanFactory;
import springIoc.config.ClassPathXmlApplicationContext;

public class UnitTest {

	public static void main(String[] args) {

		BeanFactory factory = new ClassPathXmlApplicationContext("/beansConfig.xml");
		

		User user = (User) factory.getBean("user");

		System.out.println(user.getName());
		System.out.println(user.getInfo().getMsg());
		
//		userService
		IUserService userService = (IUserService) factory.getBean("userService");
		
		System.out.println();
		userService.getUserName();

	}

}
