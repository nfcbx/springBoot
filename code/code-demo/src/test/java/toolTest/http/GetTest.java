package toolTest.http;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.joda.time.LocalDateTime;

import javax.ws.rs.core.MediaType;

public class GetTest {

    public static void main(String[] args) {
        GetTest getTest = new GetTest();

        for (int i = 0; i < 100; i++) {
            getTest.test();

        }

    }


    public void test() {
        Client client = Client.create();
        long start = System.currentTimeMillis();
//        System.out.println("开始" + LocalDateTime.now().toString());
        WebResource resource = client.resource("http://localhost:8080/1");

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
