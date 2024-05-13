package nst.springboot.domaci.controller;

import jakarta.validation.Valid;
import nst.springboot.domaci.dto.SubjectDto;
import nst.springboot.domaci.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody SubjectDto subject) throws Exception{
        SubjectDto subjectDto = subjectService.save(subject);
        return new ResponseEntity<>(subjectDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<SubjectDto> subjectDtos = subjectService.getAll();
        return new ResponseEntity<>(subjectDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public SubjectDto findById(@PathVariable("id") Long id) throws Exception {
        return subjectService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        subjectService.delete(id);
        return new ResponseEntity<>("Department removed!", HttpStatus.OK);
    }

}
