package dembros.containerized.demo;

import dembros.containerized.demo.domain.EmptyTask;
import dembros.containerized.demo.domain.RdTask;
import dembros.containerized.demo.domain.Task;
import dembros.containerized.demo.repository.RdTaskRepository;
import dembros.containerized.demo.request.TaskRequest;
import dembros.containerized.demo.service.TaskService;
import dembros.containerized.demo.service.TaskServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

  @Mock
  private RdTaskRepository repository;

  @Rule
  public MockitoRule rule = MockitoJUnit.rule();

  @Test
  public void createShouldReturnPersistedTaskFromRedis() {

    final Instant now = Instant.now().plus(1, ChronoUnit.DAYS);

    final TaskService service = new TaskServiceImpl(repository);

    doReturn(new RdTask(UUID.randomUUID().toString(), "this is a task " +
        "description", now, "PENDING"))
        .when(repository)
        .save(any(RdTask.class));

    final Task task =
        service.create(new TaskRequest("this is a task description", now));

    assertThat(task)
        .extracting("description")
        .containsExactly("this is a task description");
  }

  @Test
  public void fetchShouldReturnPersistedTaskFromRedis() {

    final Instant now = Instant.now().plus(1, ChronoUnit.DAYS);

    final TaskService service = new TaskServiceImpl(repository);

    doReturn(new RdTask(UUID.randomUUID().toString(), "this is a task " +
        "description", now, "PENDING"))
        .when(repository)
        .findOne(any());

    final Task task = service.fetch(UUID.randomUUID()).orElse(new EmptyTask());

    assertThat(task)
        .extracting("description")
        .containsExactly("this is a task description");
  }
}
