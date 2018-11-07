package hashMapTest.HashMapUnsafeTest;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZSX on 2018/11/7.
 *
 * @author ZSX
 */
public class MapThread extends Thread {
    /**
     * 类的静态变量是各个实例共享的，因此并发的执行此线程一直在操作这两个变量
     * 选择AtomicInteger避免可能的int++并发问题
     */
    private static AtomicInteger ai = new AtomicInteger(0);
    //初始化一个table长度为1的哈希表
    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(1);
    //如果使用ConcurrentHashMap，不会出现类似的问题
//       private static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>(1);

    public void run() {
        while (ai.get() < 100000) {  //不断自增
            map.put(ai.get(), ai.get());
//            map.put(1, ai.get());
//            map.get(1);
            ai.incrementAndGet();

        }

        System.out.println(Thread.currentThread().getName() + "线程即将结束");
    }
}