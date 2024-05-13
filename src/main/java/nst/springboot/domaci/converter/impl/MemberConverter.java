package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.MemberDto;
import nst.springboot.domaci.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberConverter implements DtoEntityConverter<MemberDto, Member> {
    private final AcademicTitleConverter academicTitleConverter;
    private final EducationTitleConverter educationTitleConverter;
    private final ScientificFieldConverter scientificFieldConverter;
    private final DepartmentConverter departmentConverter;

    public MemberConverter(AcademicTitleConverter academicTitleConverter, EducationTitleConverter educationTitleConverter, ScientificFieldConverter scientificFieldConverter, DepartmentConverter departmentConverter) {
        this.academicTitleConverter = academicTitleConverter;
        this.educationTitleConverter = educationTitleConverter;
        this.scientificFieldConverter = scientificFieldConverter;
        this.departmentConverter = departmentConverter;
    }

    @Override
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getFirstName(), member.getLastName(), academicTitleConverter.toDto(member.getAcademicTitle()),
                educationTitleConverter.toDto(member.getEducationTitle()), scientificFieldConverter.toDto(member.getScientificField()),
                departmentConverter.toDto(member.getDepartment()));
    }

    @Override
    public Member toEntity(MemberDto memberDto) {
        return new Member(memberDto.getId(), memberDto.getFirstName(), memberDto.getLastName(), academicTitleConverter.toEntity(memberDto.getAcademicTitleDto()),
                educationTitleConverter.toEntity(memberDto.getEducationTitleDto()), scientificFieldConverter.toEntity(memberDto.getScientificFieldDto()),
                departmentConverter.toEntity(memberDto.getDepartmentDto()));
    }
}
