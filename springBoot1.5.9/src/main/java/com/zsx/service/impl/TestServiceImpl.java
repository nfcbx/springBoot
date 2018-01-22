package com.zsx.service.impl;

import com.zsx.service.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by highness on 2018/1/22 0022.
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public String test(String name) {
        return name;
    }
}
