package dembros.containerized.demo.service;

import dembros.containerized.demo.domain.Task;
import dembros.containerized.demo.request.TaskRequest;

import java.util.Optional;
import java.util.UUID;

public interface TaskService {

  /**
   * @param request
   * @return
   */
  Task create(TaskRequest request);

  /**
   * @param identifier
   * @return
   */
  Optional<Task> fetch(UUID identifier);
}
