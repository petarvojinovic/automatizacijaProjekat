package nst.springboot.domaci.controller;

import jakarta.validation.Valid;
import nst.springboot.domaci.dto.DirectorHistoryDto;
import nst.springboot.domaci.service.DirectorHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directorHistory")
public class DirectorHistoryController {
    private final DirectorHistoryService directorHistoryService;

    public DirectorHistoryController(DirectorHistoryService directorHistoryService) {
        this.directorHistoryService = directorHistoryService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody DirectorHistoryDto directorHistoryDto) throws Exception{
        DirectorHistoryDto dto = directorHistoryService.save(directorHistoryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<DirectorHistoryDto> dtos = directorHistoryService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Object> getAllByPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "department_id") String sortBy,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection){
        Pageable pageable;
        if (sortDirection.equals("asc")) {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending());
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending());
        }
        List<DirectorHistoryDto> dtos = directorHistoryService.getAll(pageable);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/query")
    public DirectorHistoryDto findById(@RequestParam("departmentId") Long departmentId, @RequestParam("memberId") Long memberId) throws Exception {
        return directorHistoryService.findById(departmentId, memberId);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("departmentId") Long departmentId, @RequestParam("memberId") Long memberId) throws Exception{
        directorHistoryService.delete(departmentId, memberId);
        return new ResponseEntity<>("Director history removed!", HttpStatus.OK);
    }
}
