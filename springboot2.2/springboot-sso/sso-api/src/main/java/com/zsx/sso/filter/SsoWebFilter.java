package com.zsx.sso.filter;

import com.zsx.sso.SsoUser;
import com.zsx.sso.helper.SsoLoginHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SsoWebFilter extends HttpServlet implements Filter {

    private static Logger logger = LoggerFactory.getLogger(SsoWebFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("SsoWebFilter init");
//        filterConfig.getInitParameter("")
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        logger.info("SsoWebFilter doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // make url
        String servletPath = request.getServletPath();


        SsoUser ssoUser = SsoLoginHelper.loginCheck(request, response);

        if (ssoUser == null) {
            // response
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println("{\"code\":501, \"msg\":\"请登录\"}");
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
        return;
    }
}
