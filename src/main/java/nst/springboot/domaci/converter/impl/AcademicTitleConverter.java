package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.AcademicTitleDto;
import nst.springboot.domaci.model.AcademicTitle;
import org.springframework.stereotype.Component;

@Component
public class AcademicTitleConverter implements DtoEntityConverter<AcademicTitleDto, AcademicTitle> {
    @Override
    public AcademicTitleDto toDto(AcademicTitle academicTitle) {
        return new AcademicTitleDto(academicTitle.getId(), academicTitle.getName());
    }

    @Override
    public AcademicTitle toEntity(AcademicTitleDto academicTitleDto) {
        return new AcademicTitle(academicTitleDto.getId(), academicTitleDto.getName());
    }
}
