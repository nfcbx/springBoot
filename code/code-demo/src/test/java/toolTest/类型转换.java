package toolTest;

import org.junit.Test;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/24 15:38
 **/
public class 类型转换 {


    /**
     * 静态转型
     */
    @Test
    public void t1() {
        Object obj = new Integer(12);
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            System.out.println(num);
        }
    }

    /**
     * 动态转型
     * <p>
     * 有一种不常见的技术，即使用Class的方法，这些方法与上面的操作符的作用是一致的。
     * <p>
     * 注意，这个例子中类型的转换也是在编译期确定的，所以没有必要这么去做。
     */
    @Test
    public void t2() {
        Object obj = new Integer(12);
        if (Integer.class.isInstance(obj)) {
            Integer num = Integer.class.cast(obj);
            System.out.println(num);
        }
    }


    /**
     * Class<T> type = // may be Integer.class
     * <p>
     * 因为转换的类型在编译期是不知道，所以我们将这种转型称之为动态转型。
     */
    @Test
    public void t3() {
        Object obj = new Integer(12);
        Class<Integer> type = Integer.class;
        if (type.isInstance(obj)) {
            Integer num = type.cast(obj);
            System.out.println(num);
        }
    }

}
