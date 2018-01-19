package com.zsx.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zsx.config.DataSourceEnum;
import com.zsx.config.TargetDataSource;
import com.zsx.dao.MonitorHeartbeatLogMapper;
import com.zsx.dao.UserDao;
import com.zsx.entity.MonitorHeartbeatLog;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MonitorHeartbeatLogMapper monitorHeartbeatLogMapper;

    @Override
    public List<User> getAll() {
        return userDao.selectAll();
    }

    @Override
    @TargetDataSource(DataSourceEnum.SLAVE)
    public List<User> getAllSlave() {
        return userDao.selectAll();
    }

    @Override
    public PageInfo<MonitorHeartbeatLog> getLogPageList(Map<String, Object> search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MonitorHeartbeatLog> pageList = monitorHeartbeatLogMapper.queryForList(search);
        return new PageInfo(pageList);
    }

    @Override
    @TargetDataSource(DataSourceEnum.SLAVE)
    public PageInfo<MonitorHeartbeatLog> getLogPageListSlave(Map<String, Object> search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MonitorHeartbeatLog> pageList = monitorHeartbeatLogMapper.queryForList(search);
        return new PageInfo(pageList);
    }


}
