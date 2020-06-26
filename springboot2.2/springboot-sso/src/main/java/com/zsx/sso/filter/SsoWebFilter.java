package com.zsx.sso.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class SsoWebFilter extends HttpServlet implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SsoWebFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("SsoWebFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("SsoWebFilter doFilter");


        chain.doFilter(request, response);
        return;
    }
}
