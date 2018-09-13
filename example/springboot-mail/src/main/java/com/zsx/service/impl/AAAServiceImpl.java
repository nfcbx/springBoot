package com.zsx.service.impl;

import com.zsx.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * Created by ZSX on 2018/9/7.
 *
 * @author ZSX
 */
@Service("aaa")
public class AAAServiceImpl implements DemoService{
    @Override
    public String getName(String name) {
        return name + "AAA";
    }
}
