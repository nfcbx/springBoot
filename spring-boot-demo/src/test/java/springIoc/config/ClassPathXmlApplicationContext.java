package springIoc.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import groovy.lang.BenchmarkInterceptor;

public class ClassPathXmlApplicationContext implements BeanFactory {
	
//	定义一个ioc容器
	private Map<String, Object> ioc;
	
	private Map<String, Bean> config;
	
	/**
	 * 构造函数
	 * 1、初始化ioc容器
	 * 2、加载配置文件，生成bean对象放入ioc容器
	 * @param path
	 */
	public ClassPathXmlApplicationContext(String path) {
		ioc = new HashMap<String, Object>();
//		读取配置文件
		config = XmlConfig.getConfig(path);
		
		if (config != null) {
			for (Entry<String, Bean> entry : config.entrySet()) {
				String beanId = entry.getKey();
				Bean bean = entry.getValue();
				
//				根据bean生成相应的对象
				Object object = createBean(bean);
				ioc.put(beanId, object);
			}
		}
	}
	
	
	private Object createBean(Bean bean) {
		String beanId = bean.getId();
		String className = bean.getClassName();
		
		Class cls = null;
		Object object = null;
		
		try {
			//根据bean的calss属性生成对象
			cls = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("配置的class属性不合法： " + className);
		}		
		
		try {
			//该方法调用的是类的无参构造方法
			object = cls.newInstance();
		} catch (Exception e) {
			throw new RuntimeException("该类缺少一个无参构造方法："+className);
		}
		
		//将bean的属性封装到对象中
		if (bean.getProperties() != null) {
			for(Property p : bean.getProperties()){
//				情况一：配置文件中使用的是value属性注入
				if (p.getValue() != null) {
					//获取属性对应的getter方法
					Method getMethod = BeanUtil.getSetterMethod(object, p.getName());
					try {
//						调用set方法注入
						getMethod.invoke(object, p.getValue());
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("属性名称不合法或者没有相应的getter方法："+p.getName());
					}
				}
				
				//情况二：配置文件中使用的是ref属性注入
				if (p.getRef() != null) {
					
					Method getMethod = BeanUtil.getSetterMethod(object, p.getName());
					//从容器中找到依赖的对象
					Object obj = ioc.get(p.getRef());
					if (obj == null) {
						throw new RuntimeException("没有找到依赖的对象："+p.getRef());
					}else{
						try {
							//调用set方法注入
							getMethod.invoke(object, obj);
						} catch (Exception e) {
							e.printStackTrace();
							throw new RuntimeException("属性名称不合法或者没有相应的getter方法："+p.getName());
						}
					}
					
				}
			}
		}
		return object;
	}



	@Override
	public Object getBean(String beanName) {
		return ioc.get(beanName);
	}

}


class BeanUtil {
	
	/**
	 * 使用反射生成setter方法
	 * @param object
	 * @param name
	 * @return
	 */
	public static Method getSetterMethod(Object object, String name) {
		Method method = null;
		
		//set方法名：set+属性名称（首字母大写）
		String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
		try {
			Field f = object.getClass().getDeclaredField(name);
			method = object.getClass().getMethod(methodName, f.getType());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("属性名称不合法或者没有相应的getter方法："+name);
		}
		return method;
	}
	
	
	
}
