package dembros.containerized.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest implements Serializable {

  @NotNull private String description;

  @NotNull private Instant limit;
}
