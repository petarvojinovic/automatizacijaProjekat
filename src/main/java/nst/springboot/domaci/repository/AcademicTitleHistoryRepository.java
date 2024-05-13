package nst.springboot.domaci.repository;

import nst.springboot.domaci.model.AcademicTitleHistory;
import nst.springboot.domaci.model.AcademicTitleHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicTitleHistoryRepository extends JpaRepository<AcademicTitleHistory, AcademicTitleHistoryId> {

    List<AcademicTitleHistory> findByMemberId(Long memberId);
}
