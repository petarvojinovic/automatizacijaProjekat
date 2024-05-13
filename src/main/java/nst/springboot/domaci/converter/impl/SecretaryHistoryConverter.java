package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.SecretaryHistoryDto;
import nst.springboot.domaci.model.SecretaryHistory;
import nst.springboot.domaci.model.SecretaryHistoryId;
import org.springframework.stereotype.Component;

@Component
public class SecretaryHistoryConverter implements DtoEntityConverter<SecretaryHistoryDto, SecretaryHistory> {
    private final DepartmentConverter departmentConverter;
    private final MemberConverter memberConverter;

    public SecretaryHistoryConverter(DepartmentConverter departmentConverter, MemberConverter memberConverter) {
        this.departmentConverter = departmentConverter;
        this.memberConverter = memberConverter;
    }

    @Override
    public SecretaryHistoryDto toDto(SecretaryHistory secretaryHistory) {
        return new SecretaryHistoryDto(departmentConverter.toDto(secretaryHistory.getDepartment()), memberConverter.toDto(secretaryHistory.getMember()),
                secretaryHistory.getStartDate(), secretaryHistory.getEndDate());
    }

    @Override
    public SecretaryHistory toEntity(SecretaryHistoryDto secretaryHistoryDto) {
        return new SecretaryHistory(new SecretaryHistoryId(secretaryHistoryDto.getDepartmentDto().getId(), secretaryHistoryDto.getMemberDto().getId()),
                departmentConverter.toEntity(secretaryHistoryDto.getDepartmentDto()),
                memberConverter.toEntity(secretaryHistoryDto.getMemberDto()),
                secretaryHistoryDto.getStartDate(), secretaryHistoryDto.getEndDate());
    }
}
