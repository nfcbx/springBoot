import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.zsx.App;
import com.zsx.dao.MonitorHeartbeatLogMapper;
import com.zsx.entity.MonitorHeartbeatLog;
import com.zsx.service.LogService;
import com.zsx.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZSX on 2018/1/19.
 *
 * @author ZSX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UnitTest {


    @Autowired
    MonitorHeartbeatLogMapper monitorHeartbeatLogMapper;
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;

    @Test
    public void test1() {
        HashMap<String, Object> map = Maps.newHashMap();
        PageHelper.startPage(1, 15, "id desc");
        List<MonitorHeartbeatLog> list = monitorHeartbeatLogMapper.queryForList(map);
        System.out.println();
        System.out.println(JSON.toJSONString(list));
        System.out.println(JSON.toJSONString(new PageInfo<MonitorHeartbeatLog>(list)));
    }

    @Test
    public void test2() {
        ArrayList<MonitorHeartbeatLog> list = Lists.newArrayList();
        for (int i = 1; i < 10; i++) {
            MonitorHeartbeatLog log = new MonitorHeartbeatLog();
            log.setId(i);
            log.setConfigId(i);
            log.setCreateTime(new Date());
            list.add(log);
        }
        int i = monitorHeartbeatLogMapper.insertBatch(list);
        System.out.println();
        System.out.println(i);
        System.out.println();
    }

    @Test
    public void test3(){
        logService.transfromLogData();
    }
}
