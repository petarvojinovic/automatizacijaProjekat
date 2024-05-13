package nst.springboot.domaci.repository;

import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubjectRepositoryTests {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void saveTest() {
        Department department = new Department(1L, "test1", "test1");
        Department saved = departmentRepository.save(department);
        Subject subject = subjectRepository.save(new Subject(1L, "test1", 4, saved));
        assertNotNull(subject);
    }

    @Test
    public void deleteTest() {
        Department department = new Department(2L, "test2", "test2");
        Department saved = departmentRepository.save(department);
        Subject subject = subjectRepository.save(new Subject(2L, "test2", 4, saved));
        assertNotNull(subject);
        subjectRepository.delete(subject);
        Optional<Subject> deleted = subjectRepository.findById(subject.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByNameTest() {
        Department department = new Department(3L, "test3", "test3");
        Department saved = departmentRepository.save(department);
        Subject subject = subjectRepository.save(new Subject(3L, "test3", 4, saved));
        assertNotNull(subject);
        Optional<Subject> fromDb = subjectRepository.findByName(subject.getName());
        assertTrue(fromDb.isPresent());
        assertEquals(subject, fromDb.get());
    }
}
