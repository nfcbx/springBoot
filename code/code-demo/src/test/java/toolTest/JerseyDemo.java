package toolTest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

public class JerseyDemo {


    @Test
    public void test1(){

        Client client = Client.create();

        WebResource resource = client.resource("http://www.baidu.com");

        ClientResponse response = resource.type(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientResponse.class);

        String responseEntity = response.getEntity(String.class);

        System.out.println(responseEntity);


    }


    @Test
    public void 节假日(){
        Client client = Client.create();

        WebResource resource = client.resource("http://tool.bitefu.net/jiari/?d=2020");

        Cookie cookie = Cookie.valueOf("");

        ClientResponse response = resource
                .cookie(cookie)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        String responseEntity = response.getEntity(String.class);

        System.out.println(responseEntity);

    }
}
