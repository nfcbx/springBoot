package com.zsx.config;

import java.lang.annotation.*;

/**
 * 在方法上使用，用于指定使用哪个数据源
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface TargetDataSource {
    /** 数据源名称 */
    DataSourceEnum value();
}
