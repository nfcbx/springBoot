import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @author zhaoshuxue3
 * @Date 2019/4/11 14:41
 **/
public class AAA {


    public static void main(String[] args) {
        System.out.println("abc".endsWith(","));
        System.out.println("abc,".endsWith(","));
        String s = "abc,";
        s += s.endsWith(",") ? "" : ",";
        System.out.println(s);


    }

}
