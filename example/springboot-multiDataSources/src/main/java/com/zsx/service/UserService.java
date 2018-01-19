package com.zsx.service;

import com.github.pagehelper.PageInfo;
import com.zsx.entity.MonitorHeartbeatLog;
import com.zsx.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by ZSX on 2018/1/18.
 *
 * @author ZSX
 */
public interface UserService {

    List<User> getAll();

    List<User> getAllSlave();


    PageInfo<MonitorHeartbeatLog> getLogPageList(Map<String, Object> search, Integer pageNum, Integer pageSize);

    PageInfo<MonitorHeartbeatLog> getLogPageListSlave(Map<String, Object> search, Integer pageNum, Integer pageSize);

}
