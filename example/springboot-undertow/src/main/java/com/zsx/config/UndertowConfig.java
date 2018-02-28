package com.zsx.config;

import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ZSX on 2018/2/28.
 *
 * @author ZSX
 */
@Configuration
public class UndertowConfig {

    @Bean
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
            @Override
            public void customize(Undertow.Builder builder) {
//                使用 Undertow 监听多个端口
                builder.addHttpListener(8080, "0.0.0.0");
//                支持 HTTP2
                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
            }
        });

        return factory;
    }

}
