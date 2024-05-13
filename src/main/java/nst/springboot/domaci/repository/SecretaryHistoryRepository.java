package nst.springboot.domaci.repository;

import nst.springboot.domaci.model.SecretaryHistory;
import nst.springboot.domaci.model.SecretaryHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretaryHistoryRepository extends JpaRepository<SecretaryHistory, SecretaryHistoryId> {
}
