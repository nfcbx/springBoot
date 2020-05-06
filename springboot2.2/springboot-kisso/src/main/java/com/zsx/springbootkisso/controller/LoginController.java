package com.zsx.springbootkisso.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.common.SSOConstants;
import com.baomidou.kisso.security.token.SSOToken;
import com.zsx.springbootkisso.entity.Tuser;
import com.zsx.springbootkisso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String home() {
//        model.addAttribute("name", UUID.randomUUID().toString());
        return "login";
    }


    @Login(action = Action.Skip)
    // 授权登录
    @PostMapping("/login")
    @ResponseBody
    public JSONObject login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "token") String token
    ) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("code", 300);
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            System.out.println("key : " + entry.getKey());
//            System.out.println("value : " + entry.getValue());
//        }


        if (StringUtils.isBlank(username)) {
            json.put("message", "用户名不能为空");
            return json;
        }
        if (StringUtils.isBlank(password)) {
            json.put("message", "密码不能为空");
            return json;
        }

        Tuser user = userService.getUser(username.trim());
        if (user == null) {
            json.put("message", "账号不存在");
            return json;
        }

        if (!password.equals(user.getPassword())) {
            json.put("message", "密码错误");
            return json;
        }

        String returnURL = request.getParameter("ReturnURL");
        String userId = user.getId().toString();
        // 设置登录 COOKIE
        SSOToken ssoToken = SSOToken.create().setIp(request)
                .setId(userId)
                .setIssuer(username);
//        SSOHelper.setCookie(request, response, ssoToken, false);

        request.setAttribute(SSOConstants.SSO_COOKIE_MAXAGE, -1);

        SSOHelper.setCookie(request, response, ssoToken, true);

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

        Cookie[] cookies = request.getCookies();
        System.out.println("打印Cookie：");
        for (Cookie cookie : cookies) {
            System.out.println(JSON.toJSONString(cookie));
        }

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

    @GetMapping("/validateCode")
    @ResponseBody
    public void validateCode() {

        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(96, 36, 4, 10);
//        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(width, height);
//        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(width, height);
        String code = captcha.getCode();
        System.out.println("生成的验证码：" + code);

        HttpSession session = request.getSession();
        session.setAttribute("validateCode", code);

        BufferedImage image = captcha.getImage();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (IOException e) {
//            logger.error("生成验证码图片出现异常，{}", e.getMessage(), e);
            e.printStackTrace();
        }
    }


    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册
     */
    @PostMapping("/signup")
    @ResponseBody
    public JSONObject signup(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "password2") String password2,
            @RequestParam(value = "code") String code
    ) {
        JSONObject json = new JSONObject();
        json.put("success", false);
        json.put("code", 300);

        if (StringUtils.isBlank(code)) {
            json.put("message", "验证码不能为空");
            return json;
        }
        code = code.trim();

        String validateCode = request.getSession().getAttribute("validateCode").toString();

        if (!validateCode.toUpperCase().equals(code.toUpperCase())) {
            json.put("message", "验证码错误");
            return json;
        }

        if (StringUtils.isBlank(username)) {
            json.put("message", "用户名不能为空");
            return json;
        }
        username = username.trim();

        if (StringUtils.isBlank(password) || StringUtils.isBlank(password2)) {
            json.put("message", "密码不能为空");
            return json;
        }

        password = password.trim();
        password2 = password2.trim();

        if (!password.equals(password2)) {
            json.put("message", "确认密码不一样，请检查后重试");
            return json;
        }

        JSONObject jsonObject = userService.addUser(username, password);
        json.putAll(jsonObject);
        return json;
    }

    @RequestMapping("/forget")
    public String forget() {
        return "forget";
    }


    @GetMapping("/user/{id}")
    @ResponseBody
    public String getUser(@PathVariable(value = "id") Integer id) {
        if (id == null) {
            return "";
        }
        return userService.getUser(id);
    }

}
