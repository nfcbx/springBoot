package com.zsx.config.datasource;

/**
 * 动态数据源操作类
 */
public class DynamicDataSourceHolder {

    private static ThreadLocal<String> dataSources = new ThreadLocal<String>();

    /**
     * 设置数据源
     *
     * @param key
     */
    public static void setDataSource(String key) {
        System.out.println("指定数据源：" + key);
        dataSources.set(key);
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public static String getDataSource() {
        return dataSources.get();
    }

    /**
     * 清空数据源
     */
    public static void clearDataSource() {
        dataSources.remove();
    }

}
