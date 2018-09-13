package com.zsx.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * Created by ZSX on 2018/9/7.
 *
 * @author ZSX
 */
@Service
public class SpringContextUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;

    public SpringContextUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(Class<?> cls) throws BeansException {
        return applicationContext.getBean(cls);
    }

    public static Object getBeansOfType(Class<?> type) throws BeansException {
        return applicationContext.getBeansOfType(type);
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


}
