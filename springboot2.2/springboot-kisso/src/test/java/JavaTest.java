import com.baomidou.kisso.SSOHelper;
import org.junit.jupiter.api.Test;

public class JavaTest {


    @Test
    public void test1() {
        String secretKey = SSOHelper.getHS512SecretKey();
        System.out.println(secretKey);


    }

}
