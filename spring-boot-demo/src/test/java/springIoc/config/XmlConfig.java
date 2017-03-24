package springIoc.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;

public class XmlConfig {

	/**
	 * 读取配置文件，主要把配置的bean存到一个map里面
	 * @param path
	 * @return
	 */
	public static Map<String, Bean> getConfig(String path) {

		Map<String, Bean> configMap = new HashMap<String, Bean>();
		
		/*
		 * 使用HashMap时，因为HashMap不是有序的，假如有A，B两个对象，B引用了A，但往bean工厂里面放的时候，先放的B，
		 * 再放A，导致读取B的配置时，找不到A就会出现异常
		 */
		configMap = new LinkedHashMap<String, Bean>();
		
		Document doc = null;
		SAXReader reader = new SAXReader();
//		利用dom4j读取xml配置文件
		InputStream in = XmlConfig.class.getResourceAsStream(path);
		try {
			doc = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("请检查xml配置文件路径是否正确");
		}
		
//		获取所有的bean节点
		String xpath = "//bean";
		
		List<Element> list = doc.selectNodes(xpath);
		if (list != null) {
//			遍历bean节点
			for (Element element : list) {
				Bean bean = new Bean();
				
				String id = element.attributeValue("id");
				String className = element.attributeValue("class");
				
//				把配置的  id， class属性封装到Bean对象中
				bean.setId(id);
				bean.setClassName(className);
				
//				获取bean里面的property节点
				List<Element> propertyList = element.elements("property");
				if (propertyList != null) {
//					遍历
					for (Element element2 : propertyList) {
						Property property = new Property();
						
						String propName = element2.attributeValue("name");
						String propValue = element2.attributeValue("value");
						String propRef = element2.attributeValue("ref");
						
						
						
//						封装
						property.setName(propName);
						property.setValue(propValue);
						property.setRef(propRef);
						
						bean.getProperties().add(property); // 添加
					}
				}
				
//				确保id不能重复
				if (configMap.containsKey(id)) {
					throw new RuntimeException("bean节点的id重复：" + id);
				}
				
//				封装所有的配置的bean
				configMap.put(id, bean);
			}
		}
		System.out.println(JSON.toJSONString(configMap));
		return configMap;
	}

	
	
	
	
}
