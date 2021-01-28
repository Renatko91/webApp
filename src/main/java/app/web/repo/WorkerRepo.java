package app.web.repo;

import app.web.domain.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface WorkerRepo extends CrudRepository<Worker, Integer> {

}
