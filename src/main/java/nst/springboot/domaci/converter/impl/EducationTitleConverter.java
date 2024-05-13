package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.EducationTitleDto;
import nst.springboot.domaci.model.EducationTitle;
import org.springframework.stereotype.Component;

@Component
public class EducationTitleConverter implements DtoEntityConverter<EducationTitleDto, EducationTitle> {
    @Override
    public EducationTitleDto toDto(EducationTitle educationTitle) {
        return new EducationTitleDto(educationTitle.getId(), educationTitle.getName());
    }

    @Override
    public EducationTitle toEntity(EducationTitleDto educationTitleDto) {
        return new EducationTitle(educationTitleDto.getId(), educationTitleDto.getName());
    }
}
