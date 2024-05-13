package nst.springboot.domaci.service;

import nst.springboot.domaci.dto.DepartmentDto;
import nst.springboot.domaci.dto.MemberDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberService {
    MemberDto save(MemberDto memberDto)throws Exception;

    List<MemberDto> getAll();

    List<MemberDto> getAll(Pageable pageable);

    void delete(Long id) throws Exception;

    void update(MemberDto memberDto)throws Exception;

    MemberDto findById(Long id)throws Exception;
}
