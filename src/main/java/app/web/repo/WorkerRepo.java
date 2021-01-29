package app.web.repo;

import app.web.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepo extends JpaRepository<Worker, Long> {
}
