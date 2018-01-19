package com.zsx.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源注册<br/>
 * 启动动态数据源请在启动类中（如SpringBootSampleApplication）
 * 添加 @Import(DynamicDataSourceRegister.class)
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    // 数据源
    private DataSource defaultDataSource;
//    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    @Autowired
    @Qualifier("dataSourceOne")
    private DataSource dataSourceOne;

    @Autowired
    @Qualifier("slave")
    private DataSource dataSourceTwo;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        System.out.println();
        System.out.println("shuxue");
        System.out.println(dataSourceOne);
        System.out.println(dataSourceTwo);
        System.out.println();
        targetDataSources.put("dataSourceOne", dataSourceOne);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSourceOne");
        // 第二个数据源
        targetDataSources.put("slave", dataSourceTwo);
        DynamicDataSourceContextHolder.dataSourceIds.add("slave");
        // 添加更多数据源
//        targetDataSources.putAll(customDataSources);
//        for (String key : customDataSources.keySet()) {
//            DynamicDataSourceContextHolder.dataSourceIds.add(key);
//        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }


    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }

    /**
     * 初始化主数据源
     *
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
//        Map<String, Object> dsMap = new HashMap<String, Object>();
//        dsMap.put("type", propertyResolver.getProperty("type"));
//        dsMap.put("driver-class-name", propertyResolver.getProperty("driver-class-name"));
//        dsMap.put("url", propertyResolver.getProperty("url"));
//        dsMap.put("username", propertyResolver.getProperty("username"));
//        dsMap.put("password", propertyResolver.getProperty("password"));

        defaultDataSource = dataSourceOne;
    }

    /**
     * 初始化更多数据源
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
//        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
//        String dsPrefixs = propertyResolver.getProperty("names");
//        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
//            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
//            DataSource ds = buildDataSource(dsMap);
//            customDataSources.put(dsPrefix, ds);
//            dataBinder(ds, env);
//        }
    }



}
