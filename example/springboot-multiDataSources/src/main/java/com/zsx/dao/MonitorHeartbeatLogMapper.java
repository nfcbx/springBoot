package com.zsx.dao;

import com.zsx.entity.MonitorHeartbeatLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MonitorHeartbeatLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MonitorHeartbeatLog record);

    int insertSelective(MonitorHeartbeatLog record);

    MonitorHeartbeatLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MonitorHeartbeatLog record);

    int updateByPrimaryKey(MonitorHeartbeatLog record);

//

    List<MonitorHeartbeatLog> queryForList(@Param("search") Map<String, Object> search);

    int insertBatch(List<MonitorHeartbeatLog> list);
}