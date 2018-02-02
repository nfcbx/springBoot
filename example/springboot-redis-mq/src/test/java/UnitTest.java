import com.alibaba.fastjson.JSON;
import com.zsx.App;
import com.zsx.config.RedisMessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZSX on 2018/2/2.
 *
 * @author ZSX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class UnitTest {

    //    @Autowired
//    StringRedisTemplate redisTemplate;
    @Autowired
    private RedisMessageSender redisMessageSender;

    @Test
    public void test1() {
        redisMessageSender.sendMessage("chat", JSON.toJSONString(new Date()));
        Map map = new HashMap(10);
        map.put("a", "字符串");
        map.put("b", 1);
        map.put("c", new BigDecimal("123.456"));
        map.put("d", new Date());

        redisMessageSender.sendMessage("chat", JSON.toJSONString(map));

    }

}

