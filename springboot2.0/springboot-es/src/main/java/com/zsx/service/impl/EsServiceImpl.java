package com.zsx.service.impl;

import com.zsx.service.EsService;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.UUID;

/**
 * @author zhaoshuxue3
 * @Date 2019/5/30 18:47
 **/
//@Service
public class EsServiceImpl implements EsService {


    @Autowired
    @Qualifier("esClient")
    private Client client;

    @Override
    public String test() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean createIndex(String indexName) {

        CreateIndexResponse response = client.admin().indices().prepareCreate(indexName).get();

        return response.isAcknowledged();
    }

    /**
     * 判断索引是否已经存在
     *
     * @param indexName
     * @return
     */
    @Override
    public boolean existIndex(String indexName) {
        IndicesExistsResponse response = client.admin().indices().prepareExists(indexName).get();
        return response.isExists();
    }

    /**
     * 删除索引
     * **警告，该操作不可逆，请谨慎操作**
     *
     * @param indexName
     * @return
     */
    @Override
    public boolean deleteIndex(String indexName) {
        DeleteIndexResponse response = client.admin().indices().prepareDelete(indexName).get();
        return response.isAcknowledged();
    }

    /**
     * 添加数据
     *
     * @param indexName
     * @param indexType
     * @param source
     * @return
     */
    @Override
    public boolean addData(String indexName, String indexType, String source) {
        IndexResponse response = client.prepareIndex(indexName, indexType)
                .setSource(source, XContentType.JSON) // json格式的字符串
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE) // 立即刷新数据
                .get();
        if (response.status() == RestStatus.CREATED) {
            return true;
        }
        return false;
    }

    /**
     * 根据 id 修改数据
     *
     * @param indexName
     * @param indexType
     * @param id
     * @param source
     * @return
     */
    @Override
    public boolean updateDataById(String indexName, String indexType, String id, String source) {

        UpdateResponse response = client.prepareUpdate(indexName, indexType, id)
                .setDoc(source, XContentType.JSON)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .get();

        if (DocWriteResponse.Result.UPDATED == response.getResult()) {
            return true;
        }
        return false;
    }

    /**
     * 根据 id 删除数据
     *
     * @param indexName
     * @param indexType
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(String indexName, String indexType, String id) {

        DeleteResponse response = client.prepareDelete(indexName, indexType, id).get();

        if (DocWriteResponse.Result.DELETED == response.getResult()) {
            return true;
        }
        return false;
    }
}
