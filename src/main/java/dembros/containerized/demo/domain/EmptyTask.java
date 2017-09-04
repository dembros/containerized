package dembros.containerized.demo.domain;

import java.time.Instant;
import java.util.UUID;

public class EmptyTask implements Task {

  @Override
  public UUID getIdentifier() {
    return null;
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public Instant getLimit() {
    return null;
  }

  @Override
  public String getStatus() {
    return null;
  }
}
