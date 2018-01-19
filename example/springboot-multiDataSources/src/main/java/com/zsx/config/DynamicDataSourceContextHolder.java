package com.zsx.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态数据源持有者
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    public static List<Object> dataSourceIds = new ArrayList<Object>();

    /**
     * 设置数据源的对应的key
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源对应的key
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }


    public static void clearDataSourceType() {
        contextHolder.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceIds.contains(dataSourceId);
    }
}
