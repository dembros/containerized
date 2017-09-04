package dembros.containerized.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

  private UUID identifier;

  private String description;

  private Instant limit;

  private String status;
}
