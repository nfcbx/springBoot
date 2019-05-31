package com.zsx.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoshuxue3
 * @Date 2019/5/31 11:36
 **/
public class TransportClientFactory extends AbstractFactoryBean<Client> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransportClientFactory.class);

    /**
     * 集群名称
     */
    private String clusterName;

    /**
     * 集群连接信息，ip:port,...
     */
    private String nodeIpInfo;

    private TransportClient client;


    @Override
    protected Client createInstance() throws Exception {
        client = new PreBuiltTransportClient(settings());

        Map<String, Integer> nodeMap = this.parseNodeIpInfo();
        for (Map.Entry<String, Integer> node : nodeMap.entrySet()) {
            try {
                client.addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(node.getKey()), node.getValue()));
            } catch (UnknownHostException e) {
                LOGGER.error("获取ES服务地址失败，{}", e.getMessage(), e);
            }
        }
        return client;
    }

    @Override
    public void destroy() throws Exception {
        if (client != null) {
            client.close();
        }
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }



    /**
     * 解析节点IP信息,多个节点用逗号隔开,IP和端口用冒号隔开
     * <p>
     * 例如：192.168.0.101:9300,192.168.0.102:9300,192.168.0.103:9300
     *
     * @return
     */
    private Map<String, Integer> parseNodeIpInfo() {
        String[] split = nodeIpInfo.split(",");
        Map<String, Integer> map = new HashMap<>(split.length);
        for (String ipInfo : split) {
            String[] ipPort = ipInfo.split(":");
            map.put(ipPort[0], Integer.parseInt(ipPort[1]));
        }
        return map;
    }

    private Settings settings() {
        if (clusterName != null && !"".equals(clusterName)) {
            return Settings.builder()
                    .put("cluster.name", clusterName)
//                    .put("client.transport.sniff",true)
                    .build();
        }
        LOGGER.error("ES集群名称为空");
        return Settings.EMPTY;
    }


    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getNodeIpInfo() {
        return nodeIpInfo;
    }

    public void setNodeIpInfo(String nodeIpInfo) {
        this.nodeIpInfo = nodeIpInfo;
    }

    public TransportClient getClient() {
        return client;
    }

    public void setClient(TransportClient client) {
        this.client = client;
    }


}
