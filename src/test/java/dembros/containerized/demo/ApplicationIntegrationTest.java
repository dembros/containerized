package dembros.containerized.demo;

import dembros.containerized.demo.domain.EmptyTask;
import dembros.containerized.demo.domain.Task;
import dembros.containerized.demo.repository.RdTaskRepository;
import dembros.containerized.demo.request.TaskRequest;
import dembros.containerized.demo.service.TaskService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationIntegrationTest extends AbstractIntegrationTest {

  @Autowired
  private TaskService taskService;

  @Autowired
  private RdTaskRepository taskRepository;

  @Test
  public void shouldPersistTaskInRedis() {
    final Task task =
        taskService.create(
            new TaskRequest("this is a task description", Instant.now().plus(1, ChronoUnit.DAYS)));

    assertThat(taskRepository.findOne(task.getIdentifier().toString()))
        .isNotNull()
        .extracting("description")
        .containsExactly("this is a task description");
  }

  @Test
  public void shouldFetchPersistedTaskFromRedis() {
    final Task task =
        taskService.create(
            new TaskRequest("this is a task description", Instant.now().plus(1, ChronoUnit.DAYS)));

    assertThat(taskService.fetch(task.getIdentifier()).orElse(new EmptyTask()))
        .extracting("description")
        .containsExactly("this is a task description");
  }
}
