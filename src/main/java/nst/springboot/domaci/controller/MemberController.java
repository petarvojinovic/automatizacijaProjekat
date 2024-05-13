package nst.springboot.domaci.controller;

import jakarta.validation.Valid;
import nst.springboot.domaci.dto.AcademicTitleHistoryDto;
import nst.springboot.domaci.dto.MemberDto;
import nst.springboot.domaci.service.AcademicTitleHistoryService;
import nst.springboot.domaci.service.MemberService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final AcademicTitleHistoryService academicTitleHistoryService;

    public MemberController(MemberService memberService, AcademicTitleHistoryService academicTitleHistoryService) {
        this.memberService = memberService;
        this.academicTitleHistoryService = academicTitleHistoryService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody MemberDto memberDto) throws Exception {
        MemberDto dto = memberService.save(memberDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<MemberDto> members = memberService.getAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Object> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection){
        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<MemberDto> members = memberService.getAll(pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public MemberDto findById(@PathVariable("id") Long id) throws Exception{
        return memberService.findById(id);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<Object> getHistory(@PathVariable("id") Long id) throws Exception{
        List<AcademicTitleHistoryDto> history = academicTitleHistoryService.getByMember(id);
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) throws Exception{
        memberService.delete(id);
        return new ResponseEntity<>("Member removed!", HttpStatus.OK);
    }
}
