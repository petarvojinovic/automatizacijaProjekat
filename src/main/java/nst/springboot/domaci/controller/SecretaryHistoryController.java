package nst.springboot.domaci.controller;

import jakarta.validation.Valid;
import nst.springboot.domaci.dto.SecretaryHistoryDto;
import nst.springboot.domaci.service.SecretaryHistoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretaryHistory")
public class SecretaryHistoryController {
    private final SecretaryHistoryService secretaryHistoryService;

    public SecretaryHistoryController(SecretaryHistoryService secretaryHistoryService) {
        this.secretaryHistoryService = secretaryHistoryService;
    }
    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody SecretaryHistoryDto secretaryHistoryDto) throws Exception{
        SecretaryHistoryDto dto = secretaryHistoryService.save(secretaryHistoryDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<SecretaryHistoryDto> dtos = secretaryHistoryService.getAll();
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
        List<SecretaryHistoryDto> dtos = secretaryHistoryService.getAll(pageable);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping("/query")
    public SecretaryHistoryDto findById(@RequestParam("departmentId") Long departmentId, @RequestParam("memberId") Long memberId) throws Exception {
        return secretaryHistoryService.findById(departmentId, memberId);
    }
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam("departmentId") Long departmentId, @RequestParam("memberId") Long memberId) throws Exception{
        secretaryHistoryService.delete(departmentId, memberId);
        return new ResponseEntity<>("Secretary history removed!", HttpStatus.OK);
    }
}
