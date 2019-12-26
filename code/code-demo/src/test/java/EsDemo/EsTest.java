package EsDemo;

import com.zsx.config.EsSearchUtil;
import org.elasticsearch.client.Client;
import org.junit.Test;

public class EsTest {


    @Test
    public void test1() {
        Client client = EsSearchUtil.getClient();
        System.out.println(client);
    }

}
