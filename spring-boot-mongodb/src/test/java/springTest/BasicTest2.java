package springTest;

import java.util.Iterator;
import java.util.UUID;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 2.基于Annotation的自动装配
自动装配主要使用@ComponentScan、@Component和@Autowired。
（1）@ComponentScan：作用在配置类上，启用组件扫描。扫描并注册标注了@Component（@Controller\@Service\@Repository）的类型。@Configuration已经应用了@Component注解。
（2）@Autowired：按类型自动装配。@Autowired和使用@Inject（JSR-330）或@Resource（JSR-250）的效果是类似的。@Autowired和@Inject默认按类型注入,@Resource默认按名称注入。

 * @author A
 *
 */
public class BasicTest2 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfigScan.class);
		
		IAppSerivce2 bean = (IAppSerivce2) context.getBean(IAppSerivce2.class);
		
		String name = bean.getName();
		System.out.println(name);
		
		

		ClassLoader classLoader = context.getClassLoader();
		System.out.println(classLoader.toString());
		
		
		
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		
		Iterator<String> iterator = beanFactory.getBeanNamesIterator();
		
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
		}
		
		
		String name2 = new AppServiceImpl2().getName();
		System.out.println(name2);
		
		context.close();
		
	}

}



@Configuration
@ComponentScan
class AppConfigScan {
	
	@Bean
	public IAppSerivce appSerivce(){
		return new AppServiceImpl();
	}
	
}


interface IAppSerivce2 {
	String getName();
}

@Component
class AppServiceImpl2 implements IAppSerivce2 {

	public String getName() {
		
		return UUID.randomUUID().toString();
	}
	
}