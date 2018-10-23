package com.zsx.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * 全局异常统一处理
 * Created by ZSX on 2018/10/22.
 *
 * @author ZSX
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object logicExceptionHandler(HttpServletRequest request, Exception e, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        JSONObject result = new JSONObject();
        result.put("success", false);
        result.put("code", "-1");
        result.put("message", e.getMessage());
        result.put("ErrorMessage", ErrorMessage.SYSTEM_EXCEPTION);
//        RestResult result = new RestResult(false, "-1", e.getMessage(), ErrorMessage.SYSTEM_EXCEPTION);
//        //如果是业务逻辑异常，返回具体的错误码与提示信息
//        if (e instanceof LogicException) {
//            LogicException logicException = (LogicException) e;
//            result.setCode(logicException.getCode());
//            result.setErrorMessage(logicException.getErrorMsg());
//            //Validator验证框架抛出的业务逻辑异常
//        } else
        if (e instanceof ConstraintViolationException) {
            String message = ((ConstraintViolationException) e).getConstraintViolations().iterator().next().getMessage();
            result.put("code", message.substring(0, 5));
            result.put("message", message.substring(6));
        } else {
            //对系统级异常进行日志记录
            logger.error("系统异常:" + e.getMessage(), e);
        }
        return JSONObject.toJSON(result);
    }

}
