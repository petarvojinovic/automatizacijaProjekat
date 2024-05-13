package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.ScientificFieldDto;
import nst.springboot.domaci.model.ScientificField;
import org.springframework.stereotype.Component;

@Component
public class ScientificFieldConverter implements DtoEntityConverter<ScientificFieldDto, ScientificField> {
    @Override
    public ScientificFieldDto toDto(ScientificField scientificField) {
        return new ScientificFieldDto(scientificField.getId(), scientificField.getName());
    }

    @Override
    public ScientificField toEntity(ScientificFieldDto scientificFieldDto) {
        return new ScientificField(scientificFieldDto.getId(), scientificFieldDto.getName());
    }
}
