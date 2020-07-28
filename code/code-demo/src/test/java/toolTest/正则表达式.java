package toolTest;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 正则表达式 {


    @Test
    public void test1() {
        String reg = "^\\d+$";

        Pattern pattern = Pattern.compile(reg);

        String str = "J12345";

        boolean matches = str.matches(reg);
        System.out.println(matches);
        Matcher matcher = pattern.matcher(str);
        boolean b = matcher.find();
        System.out.println(b);

        boolean matches1 = Pattern.matches(reg, str);
        System.out.println(matches1);


    }
}
