package dembros.containerized.demo.controller;

import dembros.containerized.demo.domain.Task;
import dembros.containerized.demo.request.TaskRequest;
import dembros.containerized.demo.response.TaskResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface TaskController {

  /**
   * Given a {@link TaskRequest}, create a {@link Task} entity.
   *
   * @param request a {@link TaskRequest}
   * @return a {@link TaskResponse} when the {@link TaskRequest} is valid.
   */
  ResponseEntity<TaskResponse> create(TaskRequest request);

  /**
   * @param identifier
   * @return
   */
  ResponseEntity<TaskResponse> fetch(UUID identifier);
}
