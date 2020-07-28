package toolTest.JAVA基准测试;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 *
 * warmupIterations	预热的迭代次数	指定预热的迭代次数	@Warmup
 * warmupBatchSize	预热批量的大小	指定预热批量的大小	@Warmup
 * warmupForks	预热模式：INDI，BULK，BULK_INDI	指定预热模式	@Warmup
 * warmupMode	预热的模式	指定预热的模式	@Warmup
 * warmupTime	预热的时间	指定预热的时间	@Warmup
 * measurementIterations	测试的迭代次数	指定测试的迭代次数	@Measurement
 * measurementBatchSize	测试批量的大小	指定测试批量的大小	@Measurement
 * measurementTime	测试的时间	指定测试的时间	@Measurement
 * mode	测试模式： Throughput（吞吐量）， AverageTime（平均时间），SampleTime（在测试中，随机进行采样执行的时间），SingleShotTime（在每次执行中计算耗时），All	指定测试的模式	@BenchmarkMode
 *
 *
 */
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
