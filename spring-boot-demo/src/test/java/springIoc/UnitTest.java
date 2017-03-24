package springIoc;

public class UnitTest {

	public static void main(String[] args) {

		BeanFactory factory = new ClassPathXmlApplicationContext("/beansConfig.xml");
		
		

		User user = (User) factory.getBean("user");

		System.out.println(user.getName());
		
//		userService
		IUserService userService = (IUserService) factory.getBean("userService");
		
		System.out.println();
		userService.getUserName();

	}

}
