package com.zsx.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Created by QDHL on 2017/6/6.
 */
public class EsClientFactory {
    private static String esHost = null;
    private static Integer esPort;
    private static String clusterName;


    private static void initParams() {
        if (null == esHost) {
            esHost = "localhost";
            esPort = 9300;
            clusterName = "zsx-es";
        }
    }

    public static Client getClient() throws Exception {
        initParams();
        System.out.println(esHost);

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName) // 设置集群名称
//                .put("client.transport.ping_timeout", "20s")
//                .put("client.transport.sniff", true)
                .build();


        TransportClient client = new PreBuiltTransportClient(settings);



//        InetSocketAddress inetSocketAddress = new InetSocketAddress(esHost, esPort);
//        client.addTransportAddress(new InetSocketTransportAddress(inetSocketAddress));

        InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName(esHost), esPort);
        client.addTransportAddress(inetSocketTransportAddress);

        client.connectedNodes();

        return client;
    }

    public static void closeClient(Client esClient){
        if (esClient != null) {
            esClient.close();
        }
    }

}
