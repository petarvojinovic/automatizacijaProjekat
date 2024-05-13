package nst.springboot.domaci.service;

import nst.springboot.domaci.converter.impl.SubjectConverter;
import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.model.Subject;
import nst.springboot.domaci.repository.DepartmentRepository;
import nst.springboot.domaci.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SubjectServiceTests {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectConverter subjectConverter;
    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void saveSuccessTest() throws Exception {
        Subject subject = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        when(subjectRepository.save(subject)).thenReturn(subject);
        when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.empty());
        Subject s = subjectConverter.toEntity(subjectService.save(subjectConverter.toDto(subject)));
        assertNotNull(s);
        assertEquals(s, subject);
    }

    @Test
    public void saveFailureTest() {
        Subject subject = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        when(subjectRepository.findByName(subject.getName())).thenReturn(Optional.of(subject));
        assertThrows(Exception.class, () -> {
            subjectService.save(subjectConverter.toDto(subject));
        });
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        Subject subject = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        subjectService.delete(subject.getId());
        verify(subjectRepository, times(1)).delete(subject);
    }

    @Test
    public void deleteFailureTest() {
        Subject subject = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            subjectService.delete(subject.getId());
        });
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        Subject subject = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.of(subject));
        Subject existed = subjectConverter.toEntity(subjectService.findById(subject.getId()));
        assertNotNull(existed);
        assertEquals(subject, existed);
    }

    @Test
    public void findByIdFailureTest() {
        Subject subject = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        when(subjectRepository.findById(subject.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            subjectService.findById(subject.getId());
        });
    }

    @Test
    public void getAllTest() {
        Subject subject1 = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        Subject subject2 = new Subject(10L, "test1", 4, new Department(2L, "test1", "test1"));
        List<Subject> subjects = Arrays.asList(subject1, subject2);
        when(subjectRepository.findAll()).thenReturn(subjects);
        List<Subject> existed = subjectService.getAll().stream().map(subjectConverter::toEntity).toList();
        assertEquals(subjects.size(), existed.size());
        for (int i = 0; i < subjects.size(); i++) {
            assertEquals(subjects.get(i), existed.get(i));
        }
    }
}
