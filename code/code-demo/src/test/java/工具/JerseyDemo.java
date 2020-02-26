package 工具;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;
import 工具.TableauTest.SiteType;
import 工具.TableauTest.TableauCredentialsType;
import 工具.TableauTest.TsRequest;
import 工具.TableauTest.TsResponse;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

public class JerseyDemo {


    @Test
    public void test1() throws JAXBException {

        TsRequest requestPayload = new TsRequest();
        TableauCredentialsType signInCredentials = new TableauCredentialsType();

        signInCredentials.setName("");
        signInCredentials.setPassword("");

        SiteType site = new SiteType();
        site.setContentUrl("");

        signInCredentials.setSite(site);

        requestPayload.setCredentials(signInCredentials);

        StringWriter writer = new StringWriter();


        JAXBContext jaxbContext = JAXBContext.newInstance(TsRequest.class, TsResponse.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.marshal(requestPayload, writer);


        String payload = writer.toString();


        Client client = Client.create();

        WebResource webResource = client.resource("");

        ClientResponse clientResponse = webResource
                .type(MediaType.TEXT_XML_TYPE)
                .post(ClientResponse.class, payload);

        String responseXML = clientResponse.getEntity(String.class);

        TsResponse tsResponse = new TsResponse();


        StringReader reader = new StringReader(responseXML);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        tsResponse = unmarshaller.unmarshal(new StreamSource(reader), TsResponse.class).getValue();

        TableauCredentialsType credentials = tsResponse.getCredentials();

        String token = credentials.getToken();

        System.out.println(token);


    }

}
