import com.zsx.App;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;

/**
 * Created by ZSX on 2018/11/13.
 *
 * @author ZSX
 * @BeforeClass 基类执行前触发
 * @AfterClass 基类执行后触发
 * @Before 每个@Test方法调用前触发
 * @After 每个@Test方法调用后触发
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@WebAppConfiguration
public class 单元测试 {

    /**
     * sw 计时器<br>
     */
    public static StopWatch sw = null;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        sw = new StopWatch();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("**************************************************************");
        System.out.println("单元测试计时统计：" + sw.prettyPrint());
        System.out.println("**************************************************************");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("开始测试-----------------");
    }

    @After
    public void tearDown() throws Exception {
        sw.stop();
        System.out.println("测试结束-----------------");
    }


    /**
     @Ignore	忽略测试方法
     @Test	执行单元测试的方法
     */
    @Ignore
    @Test
    public void testtest() throws InterruptedException {
        sw.start("休眠1秒");
        Thread.sleep(1000L);
        System.out.println(1);
    }
}
