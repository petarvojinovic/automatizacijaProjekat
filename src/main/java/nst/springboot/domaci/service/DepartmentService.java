package nst.springboot.domaci.service;

import nst.springboot.domaci.dto.DepartmentDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    DepartmentDto save(DepartmentDto departmentDto) throws Exception;

    List<DepartmentDto> getAll(Pageable pageable);

    List<DepartmentDto> getAll();

    void delete(Long id) throws Exception;

    void update(DepartmentDto department) throws Exception;

    DepartmentDto findById(Long id) throws Exception;
}
