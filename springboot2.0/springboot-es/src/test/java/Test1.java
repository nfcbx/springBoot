import com.zsx.App;
import com.zsx.config.EsClientFactory;
import com.zsx.config.TransportClientFactory;
import com.zsx.service.EsService;
import org.elasticsearch.client.Client;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaoshuxue3
 * @Date 2019/5/30 18:53
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class Test1 {


    @Autowired
    EsService esService;

    @Autowired
    TransportClientFactory esClient;

    static String indexName = "user-20190531";

    @Test
    public void test1() {
        System.out.println(8899);
        System.out.println(esService.test());
    }

    @Test
    public void test2() throws Exception {
        System.out.println(8899);
        System.out.println(EsClientFactory.getClient());
        TimeUnit.SECONDS.sleep(2L);
    }

    @Test
    public void test3() throws Exception {
        System.out.println(esClient);
        System.out.println(esClient.getClusterName());
        System.out.println(esClient.getNodeIpInfo());
    }

    @Test
    public void test4() throws Exception {

        boolean index = esService.createIndex(indexName);
        System.out.println(index);
        boolean existIndex = esService.existIndex(indexName);
        System.out.println(existIndex);
    }

    @Test
    public void test5() throws Exception {

        JSONObject json = new JSONObject();
        json.put("a", "1");
        json.put("b", "2");
        json.put("c", "3");

        boolean addData = esService.addData(indexName, "user", json.toString());
        System.out.println(addData);
    }

    @Test
    public void test6() throws Exception {

        JSONObject json = new JSONObject();
        json.put("a", "11");
        json.put("b", "22");
        json.put("c", "33");

        boolean b = esService.updateDataById(indexName, "user", "AWsNC_dEYDOKqilikSfo", json.toString());
        System.out.println(b);
    }

    @Test
    public void test7() throws Exception {
        boolean user = esService.deleteById(indexName, "user", "AWsNC_dEYDOKqilikSfo");
        System.out.println(user);
    }

    @Test
    public void test8() throws Exception {
    }


}

