import com.zsx.config.EsClientFactory;
import org.junit.Test;

/**
 * @author zhaoshuxue3
 * @Date 2019/5/31 11:23
 **/
public class EsTest {


    @Test
    public void test1() throws Exception {
        System.out.println(8899);
        System.out.println(EsClientFactory.getClient());
    }

}
