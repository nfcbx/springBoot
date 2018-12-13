package Demo.mq;//package com.qdingnet.pcloud.mq;
//
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.common.message.MessageExt;
//
//import java.nio.charset.Charset;
//import java.util.List;
//
//public class Consumer {
//    public static void main(String[] args) throws MQClientException {
//        DefaultMQPushConsumer consumer = null;
////        consumer = new DefaultMQPushConsumer("my-group");
////        consumer = new DefaultMQPushConsumer("broker-a");
//        consumer = new DefaultMQPushConsumer("broker-z");
//
//        consumer.setNamesrvAddr("10.37.253.31:9876");
////        consumer.setInstanceName("rmq-instance");
////        consumer.subscribe("demo-topic", "demo-tag");
//        consumer.subscribe("oasis_topic", "*");
////        consumer.
//
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(
//                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                for (MessageExt msg : msgs) {
//                    System.out.println(new String(msg.getBody(), Charset.forName("UTF-8")));
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.start();
//        System.out.println("Consumer Started.");
//    }
//}
