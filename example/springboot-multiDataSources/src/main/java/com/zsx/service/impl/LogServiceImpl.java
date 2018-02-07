package com.zsx.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zsx.dao.MonitorHeartbeatLogMapper;
import com.zsx.entity.MonitorHeartbeatLog;
import com.zsx.service.LogService;
import com.zsx.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ZSX on 2018/1/19.
 *
 * @author ZSX
 */
@Service
public class LogServiceImpl implements LogService {
    private static Integer firstPageNum = 1;
    private static Integer pageSize = 10000;

    @Autowired
    private MonitorHeartbeatLogMapper monitorHeartbeatLogMapper;
    @Autowired
    private UserService userService;


    @Override
    public void transfromLogData() {
//        1、获取备份库数据最大值
        HashMap<String, Object> map = Maps.newHashMap();
        PageHelper.startPage(1, 1, "id desc");
        List<MonitorHeartbeatLog> list = monitorHeartbeatLogMapper.queryForList(map);
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("查询不到最大值");
            return;
        }
//        2、根据条件查询数据
        Integer id = list.get(0).getId();
        map.clear();
        map.put("moreId", id);
        DateTime dateTime = new DateTime(2018, 2, 1, 0, 0);
        map.put("endDate", dateTime.toDate());
        PageInfo<MonitorHeartbeatLog> pageInfo = userService.getLogPageListSlave(map, firstPageNum, pageSize);
        System.out.println(JSON.toJSONString(pageInfo));
        if (pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getList())) {
//            查询结果存入备份库中
            List<MonitorHeartbeatLog> monitorHeartbeatLogs = pageInfo.getList();
            boolean flag = saveLogs(monitorHeartbeatLogs);
            if (!flag) {
                return;
            }
//            总页数
            int pages = pageInfo.getPages();
            System.out.println("总页数为： " + pages);
            for (int i = 2; i <= pages; i++) {
                System.out.println("查询第" + i + "页");
                pageInfo = userService.getLogPageListSlave(map, i, pageSize);
                flag = saveLogs(pageInfo.getList());
                if (!flag) {
                    return;
                }
            }
        }
    }

    private boolean saveLogs(List<MonitorHeartbeatLog> monitorHeartbeatLogs) {
        int num = monitorHeartbeatLogMapper.insertBatch(monitorHeartbeatLogs);
//        if (monitorHeartbeatLogs.size() != num) {
//            System.out.println("保存条数与查询条数不一致");
//            return false;
//        }
        return true;
    }
}
