import com.baomidou.kisso.SSOHelper;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;

public class JavaTest {


    @Test
    public void test1() {
        String secretKey = SSOHelper.getHS512SecretKey();
        System.out.println(secretKey);


    }

    @Test
    public void test2(){
//        if(((double)Math.abs((user.hashCode())%1000)/1000)>identitySdkMarkRatio){


        double v = ((double) Math.abs("树学网店".hashCode()) % 1000) / 1000;
        System.out.println(v);

        v = ((double) Math.abs("猫太郎么么".hashCode()) % 1000) / 1000;
        System.out.println(v);
    }

}
