package hashMapTest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZSX on 2018/10/30.
 *
 * @author ZSX
 */
public class CodeDemo {

    public static void main(String[] args) {
        Map map = new HashMap();
        NewHashMap newHashMap = new NewHashMap<>();
        newHashMap.put("asdf", "asdfasdfasd1");


        System.out.println(newHashMap);

        System.out.println(1 << 4);
        System.out.println(2 << 4);
        System.out.println(3 << 4);

    }

}
