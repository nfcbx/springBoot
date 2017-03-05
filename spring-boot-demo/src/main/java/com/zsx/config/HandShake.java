package com.zsx.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class HandShake implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		String jspCode = ((ServletServerHttpRequest) request).getServletRequest().getParameter("jspCode");  
        // 标记用户  
        //String userId = (String) session.getAttribute("userId");  
        if(jspCode!=null){  
            attributes.put("jspCode", jspCode);  
        }else{  
            return false;  
        }  
		return true;
	}

}
