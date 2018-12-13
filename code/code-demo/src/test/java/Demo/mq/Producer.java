package Demo.mq;//package com.qdingnet.pcloud.mq;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.common.message.Message;
//
//import java.nio.charset.Charset;
//
///**
// * Created by ZSX on 2018/3/5.
// *
// * @author ZSX
// */
//public class Producer {
//    public static void main(String[] args) throws MQClientException {
////        DefaultMQProducer producer = new DefaultMQProducer("my-group");
////        DefaultMQProducer producer = new DefaultMQProducer("broker-a");
////        DefaultMQProducer producer = new DefaultMQProducer("broker-z");
//        DefaultMQProducer producer = new DefaultMQProducer("oasisProducer");
//        producer.setNamesrvAddr("10.37.253.31:9876");
////        producer.setInstanceName("rmq-instance");
////        producer.setInstanceName("DefaultCluster");
//
//        producer.start();
//        try {
////            for (int i = 0; i < 3; i++) {
////                Message msg = new Message("demo-topic",// topic
////                        "demo-tag",// tag
////                        (new Date() + "Hello RocketMQ ,QuickStart" + i)
////                                .getBytes()// body
////                );
////                SendResult sendResult = producer.send(msg);
////            }
//
////            Message message = new Message("demo-topic", "demo-tag", "这是一条测试消息".getBytes());
////            Message message = new Message("1", "demo-tag", "这是一条测试消息".getBytes());
//            Message message = new Message();
//            message.setTopic("oasis_topic");
//            message.setTags("account");
////            message.setKeys("testkey");
//            JSONObject json = new JSONObject();
//            json.put("name", "zsx");
//            json.put("age", 28);
//
////            message.setBody(json.toJSONString().getBytes());
//            message.setBody(json.toJSONString().getBytes(Charset.forName("UTF-8")));
//
////            Message message2 = new Message("oasis_topic", "demo", "testkey", message.toString().getBytes(RemotingHelper.DEFAULT_CHARSET));
//
//
//
//            SendResult sendResult = producer.send(message);
//            System.out.println(sendResult.getMsgId());
//            System.out.println(JSON.toJSONString(sendResult));
//
//
////            while (true) {
////                String text = new Scanner(System.in).next();
////                Message msg = new Message("demo-topic",// topic
////                        "demo-tag",// tag
////                        text.getBytes() // body
////                );
////                SendResult sendResult = producer.send(msg);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            producer.shutdown();
//        }
//    }
//}
