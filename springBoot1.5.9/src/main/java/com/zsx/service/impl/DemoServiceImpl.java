package com.zsx.service.impl;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.zsx.service.DemoService;
import com.zsx.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by highness on 2018/1/22 0022.
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private TestService testService;

    @Override
    public String getStr(String name) {
        if ("a".equals(name)){
            return "success";
        }
        String test = testService.test(name);
        System.out.println(test);
        return "error";
    }

}
