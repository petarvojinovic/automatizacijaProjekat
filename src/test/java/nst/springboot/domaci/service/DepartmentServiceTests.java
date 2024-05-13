package nst.springboot.domaci.service;

import nst.springboot.domaci.converter.impl.DepartmentConverter;
import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.repository.DepartmentRepository;
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
public class DepartmentServiceTests {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentConverter departmentConverter;
    @MockBean
    private DepartmentRepository departmentRepository;

    @Test
    public void saveSuccessTest() throws Exception {
        Department department = new Department(10L, "test1", "test1");
        when(departmentRepository.save(department)).thenReturn(department);
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.empty());
        Department d = departmentConverter.toEntity(departmentService.save(departmentConverter.toDto(department)));
        assertNotNull(d);
        assertEquals(d, department);
    }

    @Test
    public void saveFailureTest() {
        Department department = new Department(11L, "test2", "test2");
        when(departmentRepository.findByName(department.getName())).thenReturn(Optional.of(department));
        assertThrows(Exception.class, () -> {
            departmentService.save(departmentConverter.toDto(department));
        });
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        Department department = new Department(12L, "test3", "test3");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        departmentService.delete(department.getId());
        verify(departmentRepository, times(1)).delete(department);
    }

    @Test
    public void deleteFailureTest() {
        Department department = new Department(13L, "test4", "test4");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            departmentService.delete(department.getId());
        });
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        Department department = new Department(14L, "test5", "test5");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        Department existed = departmentConverter.toEntity(departmentService.findById(department.getId()));
        assertNotNull(existed);
        assertEquals(department, existed);
    }

    @Test
    public void findByIdFailureTest() {
        Department department = new Department(15L, "test6", "test6");
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> {
            departmentService.findById(department.getId());
        });
    }

    @Test
    public void getAllTest() {
        Department department1 = new Department(16L, "test7", "test7");
        Department department2 = new Department(16L, "test7", "test7");
        List<Department> departments = Arrays.asList(department1, department2);
        when(departmentRepository.findAll()).thenReturn(departments);
        List<Department> existed = departmentService.getAll().stream().map(departmentConverter::toEntity).toList();
        assertEquals(departments.size(), existed.size());
        for (int i = 0; i < departments.size(); i++) {
            assertEquals(departments.get(i), existed.get(i));
        }
    }
}
