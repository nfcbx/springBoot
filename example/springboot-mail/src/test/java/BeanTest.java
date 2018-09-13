import com.zsx.App;
import com.zsx.service.DemoService;
import com.zsx.util.SpringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZSX on 2018/1/17.
 *
 * @author ZSX
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ContextConfiguration
public class BeanTest {

//    @Resource(name = "aaa")
//    DemoService demoService;

    @Test
    public void sendSimpleMail() throws Exception {

        System.out.println(7788);
        System.out.println(SpringUtil.getApplicationContext());

        DemoService demoService = (DemoService) SpringUtil.getBean("aaa");

        String name = demoService.getName("123");
        System.out.println(8899);
        System.out.println(name);
    }



}
