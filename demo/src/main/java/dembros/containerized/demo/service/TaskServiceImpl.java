package dembros.containerized.demo.service;

import dembros.containerized.demo.domain.RdTask;
import dembros.containerized.demo.domain.Task;
import dembros.containerized.demo.repository.RdTaskRepository;
import dembros.containerized.demo.request.TaskRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

  private RdTaskRepository rdTaskRepository;

  public TaskServiceImpl(RdTaskRepository rdTaskRepository) {
    this.rdTaskRepository = rdTaskRepository;
  }

  @Override
  public Task create(TaskRequest request) {

    RdTask task =
        new RdTask(
            UUID.randomUUID().toString(), request.getDescription(), request.getLimit(), "PENDING");

    rdTaskRepository.save(task);

    return task;
  }

  @Override
  public Optional<Task> fetch(UUID identifier) {
    return Optional.ofNullable(rdTaskRepository.findOne(identifier.toString()));
  }
}
