package toolTest.http;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.joda.time.LocalDateTime;
import 多线程.自旋锁.LockTest4;

import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    public static void main(String[] args) throws InterruptedException {
//        GetTest getTest = new GetTest();

        for (int i = 0; i < 200; i++) {
            int num = i;
            CompletableFuture.runAsync(() -> {
                new GetTest().test(num);
            }, executorService);

        }

//        Thread.sleep(10 * 1000L);

        ConcurrentMap<String, Integer> dataMap = LockTest4.dataMap;
        for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
            System.out.println(entry.getKey() + " 次数： " + entry.getValue());
        }
    }


    public void test(Object object) {
        Client client = Client.create();
        long start = System.currentTimeMillis();
//        System.out.println("开始" + LocalDateTime.now().toString());
        WebResource resource = client.resource("http://localhost:8080/1?a=234&name=" + object);

        ClientResponse response = resource
                .type(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        String responseEntity = response.getEntity(String.class);

        System.out.println(responseEntity);
        long end = System.currentTimeMillis();

//        System.out.println("结束" + LocalDateTime.now().toString());
        System.out.println("耗时" + (end - start));
    }

}
