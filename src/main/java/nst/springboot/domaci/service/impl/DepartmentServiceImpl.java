package nst.springboot.domaci.service.impl;

import nst.springboot.domaci.converter.impl.DepartmentConverter;
import nst.springboot.domaci.dto.DepartmentDto;
import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.repository.DepartmentRepository;
import nst.springboot.domaci.service.DepartmentService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentConverter departmentConverter;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentConverter departmentConverter) {
        this.departmentRepository = departmentRepository;
        this.departmentConverter = departmentConverter;
    }

    @Override
    @Transactional
    public DepartmentDto save(DepartmentDto departmentDto) throws Exception {
        Optional<Department> department = departmentRepository.findByName(departmentDto.getName());
        if(department.isPresent()) throw new Exception("Department sa tim imenom postoji!");

        return departmentConverter.toDto(departmentRepository.save(departmentConverter.toEntity(departmentDto)));
    }

    @Override
    public List<DepartmentDto> getAll(Pageable pageable) {
        return departmentRepository.findAll(pageable).stream().map(departmentConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDto> getAll() {
        return departmentRepository.findAll().stream().map(departmentConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isEmpty()) throw new Exception("Department does not exist!");
        departmentRepository.delete(department.get());
    }

    @Override
    public void update(DepartmentDto department) throws Exception {

    }

    @Override
    public DepartmentDto findById(Long id) throws Exception {
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isEmpty()) throw new Exception("Department does not exist");
        return departmentConverter.toDto(department.get());
    }
}
