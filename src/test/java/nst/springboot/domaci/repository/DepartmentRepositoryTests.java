package nst.springboot.domaci.repository;

import nst.springboot.domaci.model.Department;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartmentRepositoryTests {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void saveTest() {
        Department department = departmentRepository.save(new Department(10L, "test1", "test1"));
        assertNotNull(department);
    }

    @Test
    public void deleteTest() {
        Department department = departmentRepository.save(new Department(11L, "test2", "test2"));
        assertNotNull(department);
        departmentRepository.delete(department);
        Optional<Department> deleted = departmentRepository.findById(department.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void findByNameTest() {
        Department department = departmentRepository.save(new Department(12L, "test3", "test3"));
        assertNotNull(department);
        Optional<Department> saved = departmentRepository.findByName(department.getName());
        assertTrue(saved.isPresent());
        assertEquals(department, saved.get());
    }
}
