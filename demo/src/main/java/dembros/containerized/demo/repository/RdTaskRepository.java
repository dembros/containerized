package dembros.containerized.demo.repository;

import dembros.containerized.demo.domain.RdTask;
import org.springframework.data.repository.CrudRepository;

public interface RdTaskRepository extends CrudRepository<RdTask, String> {}
