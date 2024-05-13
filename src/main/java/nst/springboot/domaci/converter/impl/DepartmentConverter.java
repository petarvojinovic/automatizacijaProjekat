/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.domaci.converter.impl;

import nst.springboot.domaci.converter.DtoEntityConverter;
import nst.springboot.domaci.dto.DepartmentDto;
import nst.springboot.domaci.model.Department;
import org.springframework.stereotype.Component;

/**
 *
 * @author student2
 */

@Component
public class DepartmentConverter implements DtoEntityConverter<DepartmentDto, Department> {

    @Override
    public DepartmentDto toDto(Department entity) {
        return new DepartmentDto(entity.getId(), entity.getName(), entity.getShortName());
    }

    @Override
    public Department toEntity(DepartmentDto dto) {
        return new Department(dto.getId(), dto.getName(), dto.getShortName());
    }
    
}
