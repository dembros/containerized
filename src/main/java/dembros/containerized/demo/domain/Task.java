package dembros.containerized.demo.domain;

import java.time.Instant;
import java.util.UUID;

public interface Task {

  UUID getIdentifier();

  String getDescription();

  Instant getLimit();

  String getStatus();
}
