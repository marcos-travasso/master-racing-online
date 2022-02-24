package online.masterracing.repositories;

import online.masterracing.model.Circuit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircuitRepository extends JpaRepository<Circuit, Long> {
}
