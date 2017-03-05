package com.zsx.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.google.gson.GsonBuilder;
import com.zsx.config.MyWebSocketHandler;

@Controller
public class WebSocketController {
	@Resource  
    MyWebSocketHandler myWebSocketHandler;  
    @RequestMapping(value = "GarlicPriceController/testWebSocket", method ={RequestMethod.POST,RequestMethod.GET}, produces = "application/json; charset=utf-8")  
    @ResponseBody  
    public String testWebSocket() throws IOException{  
        myWebSocketHandler.sendMessageToJsp(new TextMessage(new GsonBuilder().create().toJson("\"number\":\""+"GarlicPriceController/testWebSocket"+"\"")), "AAA");  
        return "1";  
    } 
}
