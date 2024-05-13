package nst.springboot.domaci.repository;

import nst.springboot.domaci.model.DirectorHistory;
import nst.springboot.domaci.model.DirectorHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorHistoryRepository extends JpaRepository<DirectorHistory, DirectorHistoryId> {
}
