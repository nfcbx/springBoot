package com.zsx.config.datasource;

import java.lang.annotation.*;

/**
 * 指定数据源注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TargetDataSource {
    DataSourceEnum value();
}
