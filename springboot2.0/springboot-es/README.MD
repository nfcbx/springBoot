# SpringBoot集成Elasticsearch




```
private static String esHost = null;
private static Integer esPort;
private static String clusterName;


private static void initParams() {
    if (StringUtils.isBlank(esHost)) {
        esHost = ConfigUtil.getAttribute("esHost");
        esPort = Integer.parseInt(ConfigUtil.getAttribute("esPort"));
        clusterName = ConfigUtil.getAttribute("clusterName");
    }
}

public static Client getClient() throws Exception {
    initParams();
    Settings settings = ImmutableSettings.settingsBuilder()
            .put("cluster.name", clusterName)
            .put("client.transport.sniff", true)
            .build();
    Client client = new TransportClient(settings)
            .addTransportAddress(new InetSocketTransportAddress(esHost, esPort));
    return client;
}

public static void closeClient(Client esClient){
    if (esClient != null) {
        esClient.close();
    }
}
```


```
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

```



```
<bean id="esClient" class="com.zsx.config.TransportClientFactory">
    <property name="clusterName" value="${cluster.name}"/>
    <property name="nodeIpInfo" value="${esNodeInfo}"/>
</bean>
```



```
# 自定义配置
cluster:
  name: zsx-es

esNodeInfo: localhost:9300
```

```
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ImportResource({"classpath:spring/spring.xml"})
public class AppSpringConfig implements WebMvcConfigurer {

}

```


```
@Autowired
@Qualifier("esClient")
private Client client;
```    



client.admin().indices().prepareExists(indexName).get();





```
<dependency>
    <groupId>org.elasticsearch</groupId>
    <artifactId>elasticsearch</artifactId>
    <version>5.4.3</version>
</dependency>
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>transport</artifactId>
    <version>5.4.3</version>
</dependency>
<!--
    不加下面的会报错，java.lang.NoSuchMethodError: org.elasticsearch.common.util.concurrent.EsExecutors.numberOfProcessors
    原因是上边org.elasticsearch.client:transport:5.4.3 会自动依赖 transport-netty4-client:6.4.3版本导致报错
-->
<dependency>
    <groupId>org.elasticsearch.plugin</groupId>
    <artifactId>transport-netty4-client</artifactId>
    <version>5.4.3</version>
</dependency>
```





参考文档：

https://blog.csdn.net/levae1024/article/details/79584246



https://blog.csdn.net/gong0585/article/details/72902637



