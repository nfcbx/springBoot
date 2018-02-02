package com.zsx.config;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * redis 消息处理器
 * Created by ZSX on 2018/2/2.
 *
 * @author ZSX
 */
@Component
public class MessageReceiver {
    /**
     * 接收消息的方法
     */
    public void receiveMessage(String message) {
        System.out.println("接收到：" + message);
    }
}
