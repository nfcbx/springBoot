import com.zsx.dao.UserDao;
import com.zsx.entity.User;
import com.zsx.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "file:src/main/resources/applicationContext.xml",
        "file:src/main/resources/springMVC-servlet.xml"
})
public class UnitTest {

    @Autowired
    TestService testService;
    @Autowired
    UserDao userDao;

    @Test
    public void test2() {
        testService.getDataFromMaster();
        testService.getDataFromSlave();
        testService.getDataFromMaster();
    }

    @Test
    public void test1() {
        List<User> users = userDao.selectAll();
        System.out.println(8899);
        System.out.println(users);
    }

    @Test
    public void name() {
        String test = testService.test();
        System.out.println(test);

    }

}
