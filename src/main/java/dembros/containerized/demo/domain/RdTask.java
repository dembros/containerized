package dembros.containerized.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.Instant;
import java.util.UUID;

@Data
@RedisHash("{task}")
@NoArgsConstructor
@AllArgsConstructor
public class RdTask implements Task {

  @Id private String identifier;

  private String description;

  private Instant limit;

  private String status;

  public UUID getIdentifier() {
    return UUID.fromString(this.identifier);
  }
}
