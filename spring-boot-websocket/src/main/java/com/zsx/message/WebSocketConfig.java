package com.zsx.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 允许连接的域,只能以http或https开头
        String[] allowsOrigins = {"http://127.0.0.1:1213", "http://localhost:1213"};
        registry.addHandler(gameHandler(),"/game").addInterceptors(handshakeInterceptor()).setAllowedOrigins(allowsOrigins);
    }

    @Bean
    public GameHandler gameHandler() {
        return new GameHandler();
    }

    @Bean
    public WebSocketHandshakeInterceptor handshakeInterceptor() {
        return new WebSocketHandshakeInterceptor();
    }
	
}
