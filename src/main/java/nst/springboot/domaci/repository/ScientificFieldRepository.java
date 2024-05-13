package nst.springboot.domaci.repository;

import nst.springboot.domaci.model.ScientificField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
}
