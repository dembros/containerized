package dembros.containerized.demo.domain;

import dembros.containerized.demo.response.TaskResponse;

import java.time.Instant;
import java.util.UUID;

public interface Task {

  UUID getIdentifier();

  String getDescription();

  Instant getLimit();

  String getStatus();

  default TaskResponse toResponse() {
    return new TaskResponse(
        this.getIdentifier(), this.getDescription(), this.getLimit(), this.getStatus());
  }
}
