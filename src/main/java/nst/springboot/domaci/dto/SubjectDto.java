/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.domaci.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Dules
 */

public class SubjectDto implements Serializable {
    private Long id;
    private String name;
    private int esbp;
    private DepartmentDto departmentDto;

    public SubjectDto() {
    }

    public SubjectDto(Long id, String name, int esbp, DepartmentDto departmentDto) {
        this.id = id;
        this.name = name;
        this.esbp = esbp;
        this.departmentDto = departmentDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEsbp() {
        return esbp;
    }

    public void setEsbp(int esbp) {
        this.esbp = esbp;
    }

    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    public void setDepartmentDto(DepartmentDto departmentDto) {
        this.departmentDto = departmentDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectDto that = (SubjectDto) o;
        return esbp == that.esbp && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(departmentDto, that.departmentDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, esbp, departmentDto);
    }
   
    
}
