# SpringBoot集成Elasticsearch



先放一下之前用工具类方式获取esClient的写法：

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
上面这种方式的缺点就是每次需要手动关闭连接，很麻烦。

---

下面我们用spring管理esClient的方式。

首先我们定义一个获取esClient的工厂类，在里面我们实现创建连接和销毁连接的方法

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

在application.yml里面配置es连接信息

```
# 自定义配置
cluster:
  name: es-name

esNodeInfo: localhost:9300
```


注意，这里如果是集群模式的话，配置项esNodeInfo，可以用英文逗号连接集群ip加端口号，例如：`ip1:9300,ip2:9300,ip3:9300`


我们在 `src\main\resources\spring`目录下创建一个`spring.xml`的配置文件，里面代码如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
  http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd"
       default-autowire="byName">

    <context:component-scan base-package="com.zsx"/>


    <bean id="esService" class="com.zsx.service.impl.EsServiceImpl"/>


    <!-- 定义bean -->
    <bean id="esClient" class="com.zsx.config.TransportClientFactory">
        <property name="clusterName" value="${cluster.name}"/>
        <property name="nodeIpInfo" value="${esNodeInfo}"/>
    </bean>


</beans>
```

重点是这个bean

```
<!-- 定义bean -->
<bean id="esClient" class="com.zsx.config.TransportClientFactory">
    <property name="clusterName" value="${cluster.name}"/>
    <property name="nodeIpInfo" value="${esNodeInfo}"/>
</bean>
```    

这样我们就可以把esClient工厂类放到spring容器里管理了。


因为项目是springBoot，所以需要下面导入xml配置

```
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ImportResource({"classpath:spring/spring.xml"})
public class AppSpringConfig implements WebMvcConfigurer {

}

```

最后，就可以像引入一个bean的方式使用esClient了。

```
@Autowired
@Qualifier("esClient")
private Client client;
```    


```   
使用es的api

client.admin().indices().prepareExists(indexName).get();
```   


下面是maven依赖的包，其中需要注意 transport-netty4-client 包的冲突问题。

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


---


参考文档：


https://blog.csdn.net/levae1024/article/details/79584246



https://blog.csdn.net/gong0585/article/details/72902637



