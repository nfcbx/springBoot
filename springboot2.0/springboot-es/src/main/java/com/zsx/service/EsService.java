package com.zsx.service;

/**
 * @author zhaoshuxue3
 * @Date 2019/5/30 18:47
 **/
public interface EsService {

    String test();


    /**
     * 创建索引
     *
     * @param indexName
     */
    boolean createIndex(String indexName);

    /**
     * 判断索引是否已经存在
     *
     * @param indexName
     * @return
     */
    boolean existIndex(String indexName);

    /**
     * 删除索引
     * **警告，该操作不可逆，请谨慎操作**
     *
     * @param indexName
     * @return
     */
    boolean deleteIndex(String indexName);

    /**
     * 添加数据
     *
     * @param indexName
     * @param indexType
     * @param source
     * @return
     */
    boolean addData(String indexName, String indexType, String source);


    /**
     * 根据 id 修改数据
     *
     * @param indexName
     * @param indexType
     * @param id
     * @param source
     * @return
     */
    boolean updateDataById(String indexName, String indexType, String id, String source);


    /**
     * 根据 id 删除数据
     *
     * @param indexName
     * @param indexType
     * @param id
     * @return
     */
    boolean deleteById(String indexName, String indexType, String id);
}
