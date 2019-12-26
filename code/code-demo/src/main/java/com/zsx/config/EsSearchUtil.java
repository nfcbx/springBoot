package com.zsx.config;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public class EsSearchUtil {

    private static final Logger logger = LoggerFactory.getLogger(EsSearchUtil.class);

    private static Client client = null;

    public static Client getClient() {
        if (client == null) {
            try {
                client = getTransportClient();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public static TransportClient getTransportClient() throws Exception {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch")
                .put("client.transport.sniff", true)
                .build();
        //创建client
        TransportClient client = new PreBuiltTransportClient(settings).addTransportAddresses(
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300),
                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300)
        );
        return client;
    }

    public static void closeClient(Client client) {
        if (client != null) {
            client.close();
        }
    }

    /**
     * 创建索引
     *
     * @param indexName
     * @return
     */
    public static Boolean createIndex(String indexName) {
        Client client = null;
        try {
            client = getClient();
            CreateIndexResponse response = client.admin().indices()
                    .prepareCreate(indexName).get();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es创建索引【{}】出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }

    /**
     * 创建索引（支持自定义分词器）
     *
     * @param indexName
     * @return
     */
    public static Boolean createIndexWithAnalyzer(String indexName) {
        Client client = null;
        try {
            client = getClient();
            XContentBuilder settingsBuilder = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("analysis")
                    .startObject("analyzer")
                    .startObject("ngram_analyzer")
                    .field("tokenizer", "oasis_ngram_tokenizer")
                    .endObject()
                    .endObject()

                    .startObject("tokenizer")
                    .startObject("oasis_ngram_tokenizer")
                    .field("type", "ngram")
                    .field("min_gram", 1)
                    .field("max_gram", 50)
                    //                    .field("token_chars", "letter, digit") // 不设置默认为全部
                    .endObject()
                    .endObject()
                    .endObject()
                    .endObject();

            CreateIndexResponse response = client.admin().indices()
                    .prepareCreate(indexName)
                    .setSettings(settingsBuilder)
                    .get();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es创建索引【{}】出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }

    /**
     * 判断索引是否已经存在
     *
     * @param indexName
     * @return
     */
    public static Boolean existIndex(String indexName) {
        Client client = null;
        try {
            client = getClient();
            IndicesExistsResponse existsResponse = client.admin().indices().prepareExists(indexName).get();
            return existsResponse.isExists();
        } catch (Exception e) {
            logger.error("es判断索引【{}】是否已存在时出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }

    /**
     * 删除索引 **警告，该操作不可逆，请谨慎操作**
     *
     * @param indexName
     * @return
     */
    private static Boolean deleteIndex(String indexName) {
        Client client = null;
        try {
            client = getClient();
            DeleteIndexResponse response = client.admin().indices().prepareDelete(indexName).get();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es删除索引【{}】时出现异常，{}", indexName, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }

    /**
     * 给索引添加数据类型
     *
     * @param indexName
     * @param indexType
     * @param builder
     * @return
     */
    public static Boolean addMapping(String indexName, String indexType, XContentBuilder builder) {
        Client client = null;
        try {
            client = getClient();
            PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(indexType).source(builder);
            PutMappingResponse response = client.admin().indices().putMapping(mappingRequest).actionGet();
            return response.isAcknowledged();
        } catch (Exception e) {
            logger.error("es给索引【{}】添加数据类型【{}】时出现异常，{}", indexName, indexType, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }

    /**
     * 添加数据
     *
     * @param indexName
     * @param indexType
     * @param source
     * @return
     */
    public static Boolean addData(String indexName, String indexType, String source) {
        Client client = null;
        try {
            client = getClient();
            IndexResponse response = client.prepareIndex(indexName, indexType)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                    .setSource(source).get();
            return response.getResult() == DocWriteResponse.Result.CREATED;
        } catch (Exception e) {
            logger.error("往es索引【{}】添加数据【{}】时出现异常，{}", indexName, source, e.getMessage(), e);
        } finally {
            closeClient(client);
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
    public static Boolean updateById(String indexName, String indexType, String id, String source) {
        Client client = null;
        try {
            client = getClient();
            UpdateResponse response = client.prepareUpdate(indexName, indexType, id)
//                    .setRefresh(true)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                    .setDoc(source).get();
            return response.getResult() == DocWriteResponse.Result.UPDATED;
        } catch (Exception e) {
            logger.error("修改es索引【{}】的数据【{}】时出现异常，{}", indexName, source, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }

    public static Boolean deleteById(String indexName, String indexType, String id) {
        Client client = null;
        try {
            client = getClient();
            DeleteResponse response = client.prepareDelete(indexName, indexType, id)
//                    .setRefresh(true)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                    .get();
            return response.getResult() == DocWriteResponse.Result.DELETED;
        } catch (Exception e) {
            logger.error("删除es数据【{}】时出现异常，{}", id, e.getMessage(), e);
        } finally {
            closeClient(client);
        }
        return false;
    }


}
