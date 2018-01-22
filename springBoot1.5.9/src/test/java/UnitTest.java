import com.zsx.SampleController;
import com.zsx.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by highness on 2018/1/22 0022.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleController.class)
public class UnitTest {

    @Autowired
    private DemoService demoService;


    @Test
    public void test(){
        String hello = demoService.getStr("hello");
        System.out.println();
        System.out.println(hello);
        System.out.println();
    }
}
