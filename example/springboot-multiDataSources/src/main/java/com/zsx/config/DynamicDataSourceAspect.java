package com.zsx.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 切换数据源Advice
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Aspect
//@Order(-1)// 保证该AOP在@Transactional之前执行
//@Order(1)// 保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect{
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

//    @Before("@annotation(ds)")
//    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
//        String dsId = ds.name();
//        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
//            logger.error("数据源[{}]不存在，使用默认数据源 > {}", ds.name(), point.getSignature());
//        } else {
//            logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
//            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
//        }
//    }
//
//    @After("@annotation(ds)")
//    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
//        logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
//        DynamicDataSourceContextHolder.clearDataSourceType();
//    }

    /**
     * 在数据库调用之前进行数据源设置 注：<br/>
     * 对于数据源设置采取就近原则： <br/>
     * 1、如果方法上有数据源设置则以方法为准 <br/>
     * 2、如果方法上没有进行数据源设置则以类上的数据源设置为准
     */
//    public void before(JoinPoint joinPoint) {
//        // 目标类
//        Object target = joinPoint.getTarget();
//        // 目标类Class字节码
//        Class<?> targetClass = target.getClass();
//        // 目标方法的签名
//        Signature signature = joinPoint.getSignature();
//        // 目标方法名
//        String methodName = signature.getName();
//        // 目标方法的参数类型数组
//        Class<?>[] parameterTypes = ((MethodSignature) signature).getMethod().getParameterTypes();
//
//        try {
//            TargetDataSource dataSource = null;
//            Method method = targetClass.getMethod(methodName, parameterTypes);
//            if (method != null && method.isAnnotationPresent(TargetDataSource.class)) // 获取方法上的数据源设置
//                dataSource = method.getAnnotation(TargetDataSource.class);
//            else if (targetClass.isAnnotationPresent(TargetDataSource.class)) // 获取类上的数据源设置
//                dataSource = targetClass.getAnnotation(TargetDataSource.class);
//
//            if (dataSource != null) {
//                DataSourceEnum dataSourceName = dataSource.value();
//                String key = dataSourceName.getKey();
//                DynamicDataSourceContextHolder.setDataSourceType(key);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



//    @Pointcut("execution( * com.zsx..*.*(..))")
//    public void daoAspect() {
//    }

//    @Before("@annotation(ds)")
//    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
//        DataSourceEnum dataSourceEnum = ds.value();
//        if (!DynamicDataSourceContextHolder.containsDataSource(dataSourceEnum.getKey())) {
//            logger.error("数据源[{}]不存在，使用默认数据源 > {}", dataSourceEnum.getKey(), point.getSignature());
//        } else {
//            logger.debug("Use DataSource : {} > {}", dataSourceEnum.getKey(), point.getSignature());
//            DynamicDataSourceContextHolder.setDataSourceType(dataSourceEnum.getKey());
//        }
//    }
//
//    @After("@annotation(ds)")
//    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
//        logger.debug("Revert DataSource : {} > {}", ds.value(), point.getSignature());
//        DynamicDataSourceContextHolder.clearDataSourceType();
//    }

    @Around("@annotation(ds)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, TargetDataSource ds) throws Throwable {
        try {
            logger.info("set database connection to read only");
            DynamicDataSourceContextHolder.setDataSourceType(ds.value().getKey());
            Object result = proceedingJoinPoint.proceed();
            return result;
        }finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
            logger.info("restore database connection");
        }
    }

}
