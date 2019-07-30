package com.zsx.springbootderby.config;

import com.zsx.springbootderby.dao.UserDao;
import com.zsx.springbootderby.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/30 15:46
 **/
@Component
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationReadyEventListener.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {
            userDao.create();
        } catch (Exception e1) {
            e1.printStackTrace();
            logger.error("创建表异常", e1);
        }

//        try {
//            userDao.count();
//        } catch (BadSqlGrammarException e) {
//            e.printStackTrace();
//            if (e.getSQLException().getSQLState().equals("42X05")) {
//
//            } else {
//                logger.error("创建表未知异常", e);
//            }
//        }

    }
}
