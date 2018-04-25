import com.alibaba.fastjson.JSON;
import com.zsx.App;
import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import com.zsx.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZSX on 2018/1/19.
 *
 * @author ZSX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UnitTest {


    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @Test
    public void test1() {

        List<User> userList = userService.getAll();
        System.out.println();
        System.out.println(JSON.toJSONString(userList));
        System.out.println();


        userList = userDao.selectAll();

        System.out.println();
        System.out.println(JSON.toJSONString(userList));
        System.out.println();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username","zhao");
        userList = userDao.selectByParams(map);

        System.out.println();
        System.out.println(JSON.toJSONString(userList));
        System.out.println();

        User user = userDao.selectByPrimaryKey(1L);
        System.out.println();
        System.out.println(JSON.toJSONString(user));
        System.out.println();
    }



}