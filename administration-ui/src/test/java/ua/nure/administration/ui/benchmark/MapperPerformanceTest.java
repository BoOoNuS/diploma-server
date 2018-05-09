package ua.nure.administration.ui.benchmark;

import org.dozer.DozerBeanMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.beans.BeanUtils;
import ua.nure.administration.ui.model.User;
import ua.nure.administration.ui.model.UserRole;

/**
 * Chose mapper util, by performance testing.
 *
 * @author Stanislav_Vorozhka
 */
@State(Scope.Benchmark)
public class MapperPerformanceTest {

    private DozerBeanMapper mapper = new DozerBeanMapper();

    @Benchmark
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    public void springBeanUtils() {
        User user = new User("login", "password", "Stanislav Vorozhka", UserRole.USER, false);
        User userDto = new User();
        BeanUtils.copyProperties(user, userDto);
    }

    @Benchmark
    @Warmup(iterations = 5)
    @Measurement(iterations = 10)
    public void dozer() {
        User user = new User("login", "password", "Stanislav Vorozhka", UserRole.USER, false);
        User userDto = mapper.map(user, User.class);
    }

    /** /<- 4
     * 1 <- 2 <- 3
     * Result:
     * Benchmark                               Mode  Cnt        Score       Error  Units
     * MapperPerformanceTest.dozer            thrpt  100   242689.907 ±  2548.351  ops/s
     * MapperPerformanceTest.springBeanUtils  thrpt  100  3103566.907 ± 35793.530  ops/s
     *
     * @param args the run arguments
     * @throws RunnerException the runtime exception, when something went wrong
     */
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + MapperPerformanceTest.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }
}
