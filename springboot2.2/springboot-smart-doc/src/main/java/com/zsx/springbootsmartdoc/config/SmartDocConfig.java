package com.zsx.springbootsmartdoc.config;

import com.power.doc.builder.HtmlApiDocBuilder;
import com.power.doc.constants.DocGlobalConstants;
import com.power.doc.model.ApiConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartDocConfig {

    @Bean
    public void config() {
        ApiConfig config = new ApiConfig();
        config.setServerUrl("http://localhost:48080");
        config.setAllInOne(true);

        config.setOutPath(DocGlobalConstants.HTML_DOC_OUT_PATH);

        HtmlApiDocBuilder.buildApiDoc(config);

    }

}
