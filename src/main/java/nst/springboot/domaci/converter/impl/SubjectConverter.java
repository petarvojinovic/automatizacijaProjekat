/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.SubjectDto;
import nst.springboot.domaci.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author student2
 */

@Component
public class SubjectConverter implements DtoEntityConverter<SubjectDto, Subject> {
    @Autowired
    private DepartmentConverter departmentConverter;
    
    @Override
    public SubjectDto toDto(Subject entity) {
        return new SubjectDto(
                entity.getId(), 
                entity.getName(), entity.getEspb(),
                departmentConverter.toDto(entity.getDepartment())
        );
    }

    @Override
    public Subject toEntity(SubjectDto dto) {
        return new Subject(
                dto.getId(), 
                dto.getName(), 
                dto.getEsbp(),
        departmentConverter.toEntity(dto.getDepartmentDto()));
    }
    
}
