package com.zsx.springbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/29 18:29
 **/
@RestController
public class TestController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    @GetMapping("/test")
    public void test(@RequestParam(value = "num", defaultValue = "100") int num) {

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        String message = time + " 发送消息 序号：";

        IntStream.range(1, num).parallel().forEach(i -> kafkaTemplate.send(topic, message + i));

//        String message = "test message --- " + System.currentTimeMillis();
//        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
//
//        future.addCallback(success -> LOGGER.info("发送消息成功！"), fail -> LOGGER.error("发送消息失败！"));
    }

}
