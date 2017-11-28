package es;

import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ZSX on 2017/11/3.
 *
 * @author ZSX
 */
public class EsTest {

    public static void main(String[] args) throws UnknownHostException {
//        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9300);
        InetAddress byName = InetAddress.getByName("127.0.0.1");
        InetSocketTransportAddress address = new InetSocketTransportAddress(byName, 9300);
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(address);
        System.out.println(client);

//        client.prepareGet("", "", "")

        MultiSearchResponse response = client.prepareMultiSearch()
                .add(client.prepareSearch("zsxtest")).execute().actionGet();
        System.out.println(response);
    }

}
