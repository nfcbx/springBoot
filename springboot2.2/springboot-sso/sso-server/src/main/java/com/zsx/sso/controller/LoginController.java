package com.zsx.sso.controller;

import com.zsx.sso.util.CookieUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
public class LoginController {


    @GetMapping
    public String login(HttpServletRequest request, HttpServletResponse response) {


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                System.out.println();
            }
        }

        CookieUtil.set(response, "zhao", "asdfsdfsfd_sdfasdf123", false);

        return UUID.randomUUID().toString();
    }

}
