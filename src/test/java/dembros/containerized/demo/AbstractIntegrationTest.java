package dembros.containerized.demo;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testcontainers.containers.GenericContainer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
  classes = DemoApplication.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public abstract class AbstractIntegrationTest {

  @ClassRule
  public static GenericContainer redis =
      new GenericContainer("redis:latest").withExposedPorts(6379);

  @ClassRule
  public static GenericContainer postgres =
      new GenericContainer("postgres:latest").withExposedPorts(5432);

  public static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      EnvironmentTestUtils.addEnvironment(
          "testcontainers",
          configurableApplicationContext.getEnvironment(),
          "spring.redis.host=" + redis.getContainerIpAddress(),
          "spring.redis.port=" + redis.getMappedPort(6379));
    }
  }
}
