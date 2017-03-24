package springIoc.config;

/**
 * 定义bean工厂
 * @author A
 *
 */
public interface BeanFactory {
	
	Object getBean(String beanName);

}
