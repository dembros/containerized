package dembros.containerized.demo.controller;

import dembros.containerized.demo.request.TaskRequest;
import dembros.containerized.demo.response.TaskResponse;
import dembros.containerized.demo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/v1/tasks")
public class TaskControllerImpl implements TaskController {

  private TaskService taskService;

  public TaskControllerImpl(TaskService taskService) {
    this.taskService = taskService;
  }

  @Override
  @PostMapping
  public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request) {
    return new ResponseEntity<>(taskService.create(request).toResponse(), HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<TaskResponse> fetch(UUID identifier) {
    return null;
  }
}
