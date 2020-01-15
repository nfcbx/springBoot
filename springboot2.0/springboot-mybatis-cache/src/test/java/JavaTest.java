import com.alibaba.fastjson.JSON;
import com.zsx.entity.User;
import org.mybatis.caches.redis.SerializeUtil;

public class JavaTest {

    public static void main(String[] args) {

        User user = new User(1L, "a", "b");

        byte[] bytes = SerializeUtil.serialize(user);

        System.out.println(bytes);
        System.out.println(JSON.toJSONString(bytes));
        System.out.println();
        for (byte b : bytes) {
            System.out.println(b);
        }

        Object object = SerializeUtil.unserialize(bytes);

        System.out.println(JSON.toJSONString(object));
        System.out.println(JSON.toJSONString((User) object));

    }
}
