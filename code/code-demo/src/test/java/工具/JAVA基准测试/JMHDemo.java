package 工具.JAVA基准测试;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.UUID;

public class JMHDemo {

    @Benchmark
    public void test1(){
        System.out.println(UUID.randomUUID().toString());
    }

    @Benchmark
    public void test2(){
        System.out.println(System.currentTimeMillis());
    }
}
