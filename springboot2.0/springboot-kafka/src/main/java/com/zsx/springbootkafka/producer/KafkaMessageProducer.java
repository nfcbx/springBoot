package com.zsx.springbootkafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/29 16:30
 **/
@Component
@EnableScheduling
public class KafkaMessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @Scheduled(cron = "00/5 * * * * ?")
    public void send() {
        String message = "test message --- " + System.currentTimeMillis();
        LOGGER.info("发送消息：{}", message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.addCallback(success -> LOGGER.info("发送消息成功！"), fail -> LOGGER.error("发送消息失败！"));
    }


}
