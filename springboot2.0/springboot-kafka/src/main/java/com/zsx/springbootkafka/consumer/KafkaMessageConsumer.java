package com.zsx.springbootkafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/29 16:38
 **/
@Component
public class KafkaMessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageConsumer.class);

    @KafkaListener(topics = {"${spring.kafka.template.default-topic}"})
    public void receive(@Payload String message, @Headers MessageHeaders headers) {
        LOGGER.info("接收到的消息：{}", message);
        headers.keySet().forEach(key -> LOGGER.info("{}: {}", key, headers.get(key)));
    }


}
