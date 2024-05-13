package nst.springboot.domaci.service.impl;

import nst.springboot.domaci.converter.impl.SubjectConverter;
import nst.springboot.domaci.dto.SubjectDto;
import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.model.Subject;
import nst.springboot.domaci.repository.DepartmentRepository;
import nst.springboot.domaci.repository.SubjectRepository;
import nst.springboot.domaci.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectConverter subjectConverter;

    public SubjectServiceImpl(SubjectRepository subjectRepository, DepartmentRepository departmentRepository, SubjectConverter subjectConverter) {
        this.subjectRepository = subjectRepository;
        this.departmentRepository = departmentRepository;
        this.subjectConverter = subjectConverter;
    }

    @Override
    @Transactional
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
        Subject subject = subjectConverter.toEntity(subjectDto);
        if(subject.getDepartment().getId() == null){
            departmentRepository.save(subject.getDepartment());
        } else {
            Optional<Department> dept = departmentRepository.findById(subject.getDepartment().getId());
            if(dept.isEmpty()) departmentRepository.save(subject.getDepartment());
        }
        return subjectConverter.toDto(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository.findAll().stream().map(subjectConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()) throw new Exception("Subject does not exist");
        subjectRepository.delete(subject.get());
    }

    @Override
    public void update(SubjectDto subjectDto) throws Exception {

    }

    @Override
    public SubjectDto findById(Long id) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(id);
        if(subject.isEmpty()) throw new Exception("Subject does not exist");
        return subjectConverter.toDto(subject.get());
    }
}
