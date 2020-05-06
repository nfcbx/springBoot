package 工具.JAVA基准测试;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.UUID;

public class JMHTest {


    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(JMHDemo.class.getSimpleName())
                .warmupIterations(1) // 预热5轮
                .measurementIterations(1) // 度量10轮
                .mode(Mode.Throughput)
                .forks(1)
                .build();

        new Runner(options).run();
    }


}
