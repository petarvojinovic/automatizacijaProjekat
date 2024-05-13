package nst.springboot.domaci.service.impl;

import nst.springboot.domaci.converter.impl.AcademicTitleHistoryConverter;
import nst.springboot.domaci.dto.AcademicTitleHistoryDto;
import nst.springboot.domaci.model.*;
import nst.springboot.domaci.repository.AcademicTitleHistoryRepository;
import nst.springboot.domaci.repository.AcademicTitleRepository;
import nst.springboot.domaci.repository.MemberRepository;
import nst.springboot.domaci.repository.ScientificFieldRepository;
import nst.springboot.domaci.service.AcademicTitleHistoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional(readOnly = true)
public class AcademicTitleHistoryServiceImpl implements AcademicTitleHistoryService {
    private final AcademicTitleHistoryRepository academicTitleHistoryRepository;
    private final AcademicTitleHistoryConverter academicTitleHistoryConverter;
    private final MemberRepository memberRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final ScientificFieldRepository scientificFieldRepository;

    public AcademicTitleHistoryServiceImpl(AcademicTitleHistoryRepository academicTitleHistoryRepository, AcademicTitleHistoryConverter academicTitleHistoryConverter, MemberRepository memberRepository, AcademicTitleRepository academicTitleRepository, ScientificFieldRepository scientificFieldRepository) {
        this.academicTitleHistoryRepository = academicTitleHistoryRepository;
        this.academicTitleHistoryConverter = academicTitleHistoryConverter;
        this.memberRepository = memberRepository;
        this.academicTitleRepository = academicTitleRepository;
        this.scientificFieldRepository = scientificFieldRepository;
    }

    @Override
    @Transactional
    public AcademicTitleHistoryDto save(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {
        AcademicTitleHistory academicTitleHistory = academicTitleHistoryConverter.toEntity(academicTitleHistoryDto);

        Optional<Member> memberOptional = memberRepository.findById(academicTitleHistory.getMember().getId());
        if (memberOptional.isEmpty()) throw new Exception("Member does not exist!");
        Optional<AcademicTitle> academicTitleOptional = academicTitleRepository.findById(academicTitleHistory.getAcademicTitle().getId());
        if (academicTitleOptional.isEmpty()) throw new Exception("Academic title does not exist!");
        Optional<ScientificField> scientificFieldOptional = scientificFieldRepository.findById(academicTitleHistory.getScientificField().getId());
        if (scientificFieldOptional.isEmpty()) throw new Exception("Scientific field does not exist!");

        Member existingMember = memberOptional.get();
        existingMember.setAcademicTitle(academicTitleHistory.getAcademicTitle());
        existingMember.setScientificField(academicTitleHistory.getScientificField());
        memberRepository.save(existingMember);

        Optional<AcademicTitleHistory> academicTitleHistoryOptional = academicTitleHistoryRepository.findById(
                new AcademicTitleHistoryId(academicTitleHistory.getId().getMemberId(), academicTitleHistory.getId().getAcademicTitleId()));

        if (academicTitleHistoryOptional.isEmpty()) return academicTitleHistoryConverter.toDto(academicTitleHistoryRepository.save(academicTitleHistory));

        AcademicTitleHistory existingAcademicTitleHistory = academicTitleHistoryOptional.get();
        existingAcademicTitleHistory.setStartDate(academicTitleHistory.getStartDate());
        existingAcademicTitleHistory.setEndDate(academicTitleHistory.getEndDate());
        existingAcademicTitleHistory.setScientificField(academicTitleHistory.getScientificField());
        return academicTitleHistoryConverter.toDto(academicTitleHistoryRepository.save(existingAcademicTitleHistory));
    }

    @Override
    public List<AcademicTitleHistoryDto> getAll(Pageable pageable) {
        return academicTitleHistoryRepository.findAll(pageable).stream().map(academicTitleHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleHistoryDto> getAll() {
        return academicTitleHistoryRepository.findAll().stream().map(academicTitleHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AcademicTitleHistoryDto> getByMember(Long id) {
        return academicTitleHistoryRepository.findByMemberId(id).stream().map(academicTitleHistoryConverter::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long memberId, Long academicTitleId) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistory = academicTitleHistoryRepository.findById(new AcademicTitleHistoryId(memberId, academicTitleId));
        if(academicTitleHistory.isEmpty()) throw new Exception("Academic title history does not exist!");
        academicTitleHistoryRepository.delete(academicTitleHistory.get());

    }

    @Override
    public void update(AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception {

    }

    @Override
    public AcademicTitleHistoryDto findById(Long memberId, Long academicTitleId) throws Exception {
        Optional<AcademicTitleHistory> academicTitleHistory = academicTitleHistoryRepository.findById(new AcademicTitleHistoryId(memberId, academicTitleId));
        if(academicTitleHistory.isEmpty()) throw new Exception("Academic title history does not exist!");
        return academicTitleHistoryConverter.toDto(academicTitleHistory.get());
    }
}
