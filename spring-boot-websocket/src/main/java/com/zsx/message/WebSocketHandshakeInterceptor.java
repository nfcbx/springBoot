package com.zsx.message;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse shr1, WebSocketHandler wsh,
			Map<String, Object> attributes) throws Exception {
		// 此处可以做一些权限认证的事情或者其他
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest shr, ServerHttpResponse shr1, WebSocketHandler wsh, Exception excptn) {

	}
}
