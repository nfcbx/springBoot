package com.zsx.springbootkisso.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.security.token.SSOToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    @RequestMapping(value = {"/", "/index"})
    public String home() {
//        model.addAttribute("name", UUID.randomUUID().toString());
        return "login";
    }



    // 授权登录
    @PostMapping("/login")
    @ResponseBody
    public JSONObject login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "token") String token
    ) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
        }
        String returnURL = request.getParameter("ReturnURL");
        // 设置登录 COOKIE
        SSOHelper.setCookie(request, response, SSOToken.create().setIp(request).setId(1000).setIssuer("kisso"), false);

        JSONObject json = new JSONObject();
        json.put("success", true);
        json.put("code", 200);
        json.put("message", "成功登录");
        json.put("returnURL", returnURL);
        return json;
    }

    // 查看登录信息
    @ResponseBody
    @GetMapping("/token")
    public String token() {
        String msg = "暂未登录";
        SSOToken ssoToken = SSOHelper.attrToken(request);
        if (null != ssoToken) {
            msg = "登录信息 ip=" + ssoToken.getIp();
            msg += "， id=" + ssoToken.getId();
            msg += "， issuer=" + ssoToken.getIssuer();
        }
        return msg;
    }

    // 退出登录
    @GetMapping("/logout")
    @ResponseBody
    public String logout() {
        SSOHelper.clearLogin(request, response);
        return "Logout Kisso!";
    }


    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/forget")
    public String forget() {
        return "forget";
    }


}
