package EsDemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsx.config.EsSearchUtil;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.metrics.valuecount.ValueCount;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class EsTest {

    private Client client = null;

    private static String indexName = "demo2";
    private static String indexType = "demo2";

    @Before
    public void before() {
        client = EsSearchUtil.getClient();
    }

    @Test
    public void test1() {
        System.out.println(client);

        CreateIndexResponse response = client.admin().indices()
                .prepareCreate(indexName).get();
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);

    }

    @Test
    public void test2() throws IOException {

        XContentBuilder builder = null;
        builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("properties")
                .startObject()

                .field("name").startObject().field("type", "string").field("index", "not_analyzed").endObject()
                .field("name1").startObject().field("type", "string").field("index", "not_analyzed").endObject()
                .field("name2").startObject().field("type", "string").field("index", "not_analyzed").endObject()

                .field("aaa").startObject().field("type", "string").endObject()
                .field("bbb").startObject().field("type", "string").endObject()

                .field("create_time").startObject().field("type", "long").field("index", "not_analyzed").endObject()
                .endObject()
                .endObject();

        PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(indexType).source(builder);
        PutMappingResponse response = client.admin().indices().putMapping(mappingRequest).actionGet();
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
    }

    @Test
    public void test3() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", UUID.randomUUID().toString());

        IndexResponse indexResponse = client.prepareIndex(indexName, indexType)
                .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                .setSource(jsonObject.toJSONString(), XContentType.JSON)
                .get();

        RestStatus status = indexResponse.status();
        System.out.println(JSON.toJSONString(status));
    }

    @Test
    public void test4() {

        for (int i = 0; i < 105; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", UUID.randomUUID().toString());
            jsonObject.put("name2", "1");
            jsonObject.put("aaa", i);
            IndexResponse indexResponse = client.prepareIndex(indexName, indexType)
                    .setSource(jsonObject.toJSONString(), XContentType.JSON)
                    .get();
            RestStatus status = indexResponse.status();
            System.out.println(JSON.toJSONString(status));
        }
    }


    @Test
    public void test5() throws IOException, InterruptedException {
        int size = 10;
        // 因为有5个分片，所以每次查询出来的数据条数为 5 乘以 size ，即100条
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(new QueryStringQueryBuilder("1").field("name2"));

        SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(indexType)
                .setQuery(queryBuilder)
//                .setSearchType(SearchType.SCAN)
                .setSize(size)
                .setScroll(TimeValue.timeValueMinutes(1))
                .get();

        long total = searchResponse.getHits().getTotalHits();
        SearchHit[] hits = searchResponse.getHits().getHits();

        int sum = 0;
        System.out.println("hits.length : " + hits.length);
        sum += hits.length;
        System.out.println("查询总条数为：" + total);
        while (sum < total) {
            searchResponse = client.prepareSearchScroll(searchResponse.getScrollId())
                    .setScroll(TimeValue.timeValueMinutes(1))
                    .get();
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            this.updateById(searchHits);
            TimeUnit.SECONDS.sleep(2L);
            sum += searchHits.length;
            System.out.println("已处理条数：" + sum);
        }
    }

    private void updateById(SearchHit[] hits) throws IOException {
        for (SearchHit hit : hits) {
            String id = hit.getId();
            XContentBuilder source = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("name2", "2")
                    .endObject();
            UpdateResponse updateResponse = client.prepareUpdate()
                    .setIndex(indexName).setType(indexType)
                    .setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE)
                    .setId(id)
                    .setDoc(source)
                    .get();
            RestStatus status = updateResponse.status();
            System.out.println("id= " + id + "     status= " + JSON.toJSONString(status));
        }

    }

    @Test
    public void 创建索引() throws IOException {

        String indexName = "demo2";
        String indexType = "demo2";

        client.admin().indices().prepareCreate(indexName).get();

        XContentBuilder builder = null;
        builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("properties")
                .startObject()

                .field("name").startObject().field("type", "keyword").endObject()
                .field("name1").startObject().field("type", "string").field("index", "not_analyzed").endObject()
                .field("name2").startObject().field("type", "string").endObject()

                .field("type").startObject().field("type", "string").endObject()

                .field("age").startObject().field("type", "integer").field("index", "not_analyzed").endObject()
                .field("create_time").startObject().field("type", "long").field("index", "not_analyzed").endObject()
                .endObject()
                .endObject();

        PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName).type(indexType).source(builder);
        PutMappingResponse response = client.admin().indices().putMapping(mappingRequest).actionGet();
        boolean acknowledged = response.isAcknowledged();
        System.out.println(acknowledged);
    }

    @Test
    public void 创建数据() throws IOException {
        IntStream.range(30, 40).parallel().forEach((i) -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "name" + i);
            jsonObject.put("name1", "name" + i);
            jsonObject.put("name2", "name" + i);
            jsonObject.put("type", "type3");
            jsonObject.put("age", i);
            jsonObject.put("create_time", System.currentTimeMillis());
            IndexResponse indexResponse = client.prepareIndex(indexName, indexType)
                    .setSource(jsonObject.toJSONString(), XContentType.JSON)
                    .get();
            RestStatus status = indexResponse.status();
            System.out.println(JSON.toJSONString(status));
        });

    }

    @Test
    public void 聚合数据() throws ExecutionException, InterruptedException {

//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().mustNot(QueryBuilders.matchQuery("type", "type1"));
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("type", "type1"));

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .mustNot(QueryBuilders.matchQuery("type", "type1"))
                .mustNot(QueryBuilders.matchQuery("type", "type2"));


        AggregationBuilder aggregationBuilder = AggregationBuilders.filter("name_filter", boolQueryBuilder)
                .subAggregation(AggregationBuilders.count("name_count").field("name"));

        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        SearchRequestBuilder searchRequest = client.prepareSearch(indexName).setTypes(indexType).setQuery(queryBuilder);

        searchRequest.addAggregation(aggregationBuilder);


        SearchResponse searchResponse = client.search(searchRequest.request()).get();

        System.out.println(8899);
        System.out.println(JSON.toJSONString(searchResponse));
        Aggregations aggregations = searchResponse.getAggregations();

        System.out.println(JSON.toJSONString(aggregations));
//
//        Aggregation name = aggregations.get("name_filter");
//        System.out.println(JSON.toJSONString(name));

        Filter nameFilter = (Filter) aggregations.get("name_filter");
        System.out.println(JSON.toJSONString(nameFilter));
        System.out.println(JSON.toJSONString(nameFilter.getAggregations()));

        Aggregation name_count = nameFilter.getAggregations().get("name_count");

        System.out.println(JSON.toJSONString(name_count));

        ValueCount nameCount = (ValueCount) name_count;
        System.out.println(JSON.toJSONString(nameCount));

        long value = nameCount.getValue();

        System.out.println(value);


    }

    public static void main(String[] args) {
        for (int i = 85; i < 105; i++) {
            int page = i / 10;
            if (i % 10 > 0) {
                page += 1;
            }
            System.out.println("i = " + i + "   页码为：" + page);
        }

    }

}
