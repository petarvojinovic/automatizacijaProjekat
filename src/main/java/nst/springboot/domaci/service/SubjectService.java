package nst.springboot.domaci.service;

import nst.springboot.domaci.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    SubjectDto save(SubjectDto subjectDto) throws Exception;

    List<SubjectDto> getAll();

    void delete(Long id) throws Exception;

    void update(SubjectDto subjectDto)throws Exception;

    SubjectDto findById(Long id)throws Exception;
}
