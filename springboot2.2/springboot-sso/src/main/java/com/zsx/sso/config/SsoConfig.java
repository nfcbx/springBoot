package com.zsx.sso.config;

import com.zsx.sso.filter.SsoWebFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SsoConfig implements DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(SsoConfig.class);

    @Bean
    public FilterRegistrationBean registrationBean() {
        logger.info("registrationBean method");

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setName("SsoWebFilter");
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setFilter(new SsoWebFilter());
//        registrationBean.addInitParameter("", "");

        /**
         * FilterRegistrationBean registration = new FilterRegistrationBean();
         *
         *         registration.setName("XxlSsoWebFilter");
         *         registration.setOrder(1);
         *         registration.addUrlPatterns("/*");
         *         registration.setFilter(new XxlSsoWebFilter());
         *         registration.addInitParameter(Conf.SSO_SERVER, xxlSsoServer);
         *         registration.addInitParameter(Conf.SSO_LOGOUT_PATH, xxlSsoLogoutPath);
         *         registration.addInitParameter(Conf.SSO_EXCLUDED_PATHS, xxlSsoExcludedPaths);
         *
         *         return registration;
         */
        return registrationBean;
    }


    @Override
    public void destroy() throws Exception {

    }
}
