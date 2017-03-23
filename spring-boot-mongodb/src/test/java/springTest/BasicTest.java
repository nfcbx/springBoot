package springTest;

import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.使用Java配置代替xml配置
Java配置的核心是@Configuration和@Bean。定义生命周期使用@Scope，需要引入其他配置文件时使用@Import。
（1）@Configuration：应用了@Configuration注解的POCO成为了配置类。相当于xml配置文件。
（2）@Bean：配置类中应用了@Bean注解的方法成为了配置项。相当于xml中的Bean节点。

 * @author A
 *
 */

public class BasicTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigScan.class);
		
		IAppSerivce bean = (IAppSerivce) context.getBean(IAppSerivce.class);
		
		String name = bean.getName();
		System.out.println(name);
		
		

		ClassLoader classLoader = context.getClassLoader();
		System.out.println(classLoader.toString());
		
		
//		ApplicationContext parent = context.getParent(); // null
		
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		
		Iterator<String> iterator = beanFactory.getBeanNamesIterator();
		
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
		}
		
		
		
		
		context.close();
		
	}

}

@Configuration
class AppConfig {
	
	@Bean
	public IAppSerivce appSerivce(){
		return new AppServiceImpl();
	}
	
}


interface IAppSerivce {
	String getName();
}


class AppServiceImpl implements IAppSerivce {

	public String getName() {
		
		return UUID.randomUUID().toString();
	}
	
}