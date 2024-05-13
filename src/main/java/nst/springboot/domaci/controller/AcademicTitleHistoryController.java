package nst.springboot.domaci.controller;

import jakarta.validation.Valid;
import nst.springboot.domaci.dto.AcademicTitleHistoryDto;
import nst.springboot.domaci.service.AcademicTitleHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academicTitleHistory")
public class AcademicTitleHistoryController {
    private final AcademicTitleHistoryService academicTitleHistoryService;

    public AcademicTitleHistoryController(AcademicTitleHistoryService academicTitleHistoryService) {
        this.academicTitleHistoryService = academicTitleHistoryService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody AcademicTitleHistoryDto academicTitleHistoryDto) throws Exception{
        AcademicTitleHistoryDto dto = academicTitleHistoryService.save(academicTitleHistoryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<AcademicTitleHistoryDto> dtos = academicTitleHistoryService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Object> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "member_id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection){
        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<AcademicTitleHistoryDto> dtos = academicTitleHistoryService.getAll(pageable);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/query")
    public AcademicTitleHistoryDto findById(@RequestParam("memberId") Long memberId, @RequestParam("academicTitleId") Long academicTitleId) throws Exception{
        return academicTitleHistoryService.findById(memberId, academicTitleId);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("memberId") Long memberId, @RequestParam("academicTitleId") Long academicTitleId) throws Exception{
        academicTitleHistoryService.delete(memberId, academicTitleId);
        return new ResponseEntity<>("Academic title history removed!", HttpStatus.OK);
    }
}
