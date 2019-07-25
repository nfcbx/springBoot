import com.alibaba.fastjson.JSON;
import com.zsx.App;
import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/24 18:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})
@WebAppConfiguration
public class UnitTest {

    @Autowired
    UserDao userDao;

    @Test
    public void t1() {
        List<User> users = userDao.selectAll();
        System.out.println(8899);

        System.out.println(JSON.toJSONString(users));
    }


}
