package com.zsx.config.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

public class DataSourceAspect {

    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 目标类
        Object target = joinPoint.getTarget();
        // 目标类Class字节码
        Class<?> targetClass = target.getClass();
        // 目标方法的签名
        Signature signature = joinPoint.getSignature();
        // 目标方法名
        String methodName = signature.getName();
        // 目标方法的参数类型数组
        Class<?>[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();

        TargetDataSource targetDataSource = null;
        try {
            Method method = targetClass.getMethod(methodName, parameterTypes);
            if (method != null && method.isAnnotationPresent(TargetDataSource.class)) {
                targetDataSource = method.getAnnotation(TargetDataSource.class);
            } else if (targetClass.isAnnotationPresent(TargetDataSource.class)) {
                targetDataSource = targetClass.getAnnotation(TargetDataSource.class);
            }

            if (targetDataSource != null) {
                DataSourceEnum dataSource = targetDataSource.value();
                DynamicDataSourceHolder.setDataSource(dataSource.getKey());
            }
            return joinPoint.proceed();
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }
}
