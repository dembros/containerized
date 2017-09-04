package dembros.containerized.demo;

import dembros.containerized.demo.domain.Task;
import dembros.containerized.demo.request.TaskRequest;
import dembros.containerized.demo.service.TaskService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DemoApplicationTests extends AbstractIntegrationTest {

  @Autowired private TaskService taskService;

  @Test
  public void shouldCreateValidTask() {
    Task task =
        taskService.create(
            new TaskRequest("this is a task description", Instant.now().plus(1, ChronoUnit.DAYS)));

    assertThat(taskService.fetch(task.getIdentifier())).isNotEqualTo(Optional.empty());
  }
}
