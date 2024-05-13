package nst.springboot.domaci.service.impl;

import nst.springboot.domaci.converter.impl.DirectorHistoryConverter;
import nst.springboot.domaci.dto.DirectorHistoryDto;
import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.model.DirectorHistory;
import nst.springboot.domaci.model.DirectorHistoryId;
import nst.springboot.domaci.model.Member;
import nst.springboot.domaci.repository.DepartmentRepository;
import nst.springboot.domaci.repository.DirectorHistoryRepository;
import nst.springboot.domaci.repository.MemberRepository;
import nst.springboot.domaci.service.DirectorHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DirectorHistoryServiceImpl implements DirectorHistoryService {
    private final DirectorHistoryRepository directorHistoryRepository;
    private final DirectorHistoryConverter directorHistoryConverter;
    private final DepartmentRepository departmentRepository;
    private final MemberRepository memberRepository;

    public DirectorHistoryServiceImpl(DirectorHistoryRepository directorHistoryRepository, DirectorHistoryConverter directorHistoryConverter, DepartmentRepository departmentRepository, MemberRepository memberRepository) {
        this.directorHistoryRepository = directorHistoryRepository;
        this.directorHistoryConverter = directorHistoryConverter;
        this.departmentRepository = departmentRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public DirectorHistoryDto save(DirectorHistoryDto directorHistoryDto) throws Exception {
        DirectorHistory directorHistory = directorHistoryConverter.toEntity(directorHistoryDto);

        Optional<Member> memberOptional = memberRepository.findById(directorHistory.getId().getMemberId());
        if (memberOptional.isEmpty()) throw new Exception("Member does not exist!");
        Optional<Department> departmentOptional = departmentRepository.findById(directorHistory.getId().getDepartmentId());
        if (departmentOptional.isEmpty()) throw new Exception("Department does not exist!");

        Optional<DirectorHistory> directorHistoryOptional = directorHistoryRepository.findById(new DirectorHistoryId(directorHistory.getId().getDepartmentId(),
                directorHistory.getId().getMemberId()));
        if(directorHistoryOptional.isEmpty()) return directorHistoryConverter.toDto(directorHistoryRepository.save(directorHistory));
        DirectorHistory existingDirectorHistory = directorHistoryOptional.get();
        existingDirectorHistory.setStartDate(directorHistory.getStartDate());
        existingDirectorHistory.setEndDate(directorHistory.getEndDate());
        return directorHistoryConverter.toDto(directorHistoryRepository.save(existingDirectorHistory));
    }

    @Override
    public List<DirectorHistoryDto> getAll(Pageable pageable) {
        return directorHistoryRepository.findAll(pageable).stream().map(directorHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DirectorHistoryDto> getAll() {
        return directorHistoryRepository.findAll().stream().map(directorHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long departmentId, Long memberId) throws Exception {
        Optional<DirectorHistory> directorHistory = directorHistoryRepository.findById(new DirectorHistoryId(departmentId, memberId));
        if(directorHistory.isEmpty()) throw new Exception("Director history does not exist!");
        directorHistoryRepository.delete(directorHistory.get());
    }

    @Override
    public void update(DirectorHistoryDto directorHistoryDto) throws Exception {

    }

    @Override
    public DirectorHistoryDto findById(Long departmentId, Long memberId) throws Exception {
        Optional<DirectorHistory> directorHistory = directorHistoryRepository.findById(new DirectorHistoryId(departmentId, memberId));
        if(directorHistory.isEmpty()) throw new Exception("Director history does not exist!");
        return directorHistoryConverter.toDto(directorHistory.get());
    }
}
