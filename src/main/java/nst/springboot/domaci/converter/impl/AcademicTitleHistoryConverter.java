package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.AcademicTitleHistoryDto;
import nst.springboot.domaci.model.*;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleHistoryConverter implements DtoEntityConverter<AcademicTitleHistoryDto, AcademicTitleHistory> {
    private final MemberConverter memberConverter;
    private final AcademicTitleConverter academicTitleConverter;
    private final ScientificFieldConverter scientificFieldConverter;

    public AcademicTitleHistoryConverter(MemberConverter memberConverter, AcademicTitleConverter academicTitleConverter, ScientificFieldConverter scientificFieldConverter) {
        this.memberConverter = memberConverter;
        this.academicTitleConverter = academicTitleConverter;
        this.scientificFieldConverter = scientificFieldConverter;
    }

    @Override
    public AcademicTitleHistoryDto toDto(AcademicTitleHistory academicTitleHistory) {
        return new AcademicTitleHistoryDto(memberConverter.toDto(academicTitleHistory.getMember()), academicTitleConverter.toDto(academicTitleHistory.getAcademicTitle()),
                academicTitleHistory.getStartDate(), academicTitleHistory.getEndDate(), scientificFieldConverter.toDto(academicTitleHistory.getScientificField()));
    }

    @Override
    public AcademicTitleHistory toEntity(AcademicTitleHistoryDto academicTitleHistoryDto) {
        return new AcademicTitleHistory(new AcademicTitleHistoryId(academicTitleHistoryDto.getMemberDto().getId(), academicTitleHistoryDto.getAcademicTitleDto().getId()),
                memberConverter.toEntity(academicTitleHistoryDto.getMemberDto()),
                academicTitleConverter.toEntity(academicTitleHistoryDto.getAcademicTitleDto()),
                academicTitleHistoryDto.getStartDate(), academicTitleHistoryDto.getEndDate(),
                scientificFieldConverter.toEntity(academicTitleHistoryDto.getScientificFieldDto()));
    }
}
