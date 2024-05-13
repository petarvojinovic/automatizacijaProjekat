package nst.springboot.domaci.service;

import nst.springboot.domaci.dto.DirectorHistoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DirectorHistoryService {
    DirectorHistoryDto save(DirectorHistoryDto directorHistoryDto) throws Exception;

    List<DirectorHistoryDto> getAll(Pageable pageable);

    List<DirectorHistoryDto> getAll();

    void delete(Long departmentId, Long memberId) throws Exception;

    void update(DirectorHistoryDto directorHistoryDto) throws Exception;

    DirectorHistoryDto findById(Long departmentId, Long memberId) throws Exception;
}
