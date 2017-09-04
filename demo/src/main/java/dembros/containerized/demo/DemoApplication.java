package dembros.containerized.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import java.util.UUID;

@Configuration
@SpringBootApplication
@EnableRedisRepositories
@EnableAutoConfiguration
public class DemoApplication {

  @Value("${spring.redis.host}")
  private String redisHost;

  @Value("${spring.redis.port}")
  private Integer redisPort;

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public RedisConnectionFactory connectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(redisHost);
    factory.setPort(redisPort);
    factory.setUsePool(true);
    return factory;
  }

  @Bean
  public RedisTemplate<Object, Object> redisTemplate() {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    template.setHashKeySerializer(new GenericToStringSerializer<>(UUID.class));
    template.setKeySerializer(new GenericToStringSerializer<>(UUID.class));
    template.setHashValueSerializer(new GenericToStringSerializer<>(Object.class));
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
    return template;
  }
}
