package toolTest.JAVA基准测试;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 官方文档：http://openjdk.java.net/projects/code-tools/jmh/
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)

@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例

public class JMHDemo {

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(JMHDemo.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试

    }

    @Benchmark
    public void test1() {
        System.out.println(UUID.randomUUID().toString());
    }

    @Benchmark
    public void test2() {
        System.out.println(System.currentTimeMillis());
    }
}
