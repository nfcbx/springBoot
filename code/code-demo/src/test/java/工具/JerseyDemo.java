package 工具;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

public class JerseyDemo {


    @Test
    public void test1() {

        Client client = Client.create();

        WebResource webResource = client.resource("");

        ClientResponse clientResponse = webResource
                .type(MediaType.TEXT_XML_TYPE)
                .post(ClientResponse.class, "");

        String responseXML = clientResponse.getEntity(String.class);

    }

}
