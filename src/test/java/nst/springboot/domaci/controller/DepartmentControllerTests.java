package nst.springboot.domaci.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nst.springboot.domaci.converter.impl.DepartmentConverter;
import nst.springboot.domaci.dto.DepartmentDto;
import nst.springboot.domaci.model.Department;
import nst.springboot.domaci.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTests {
    @MockBean
    private DepartmentService departmentService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveSuccessTest() throws Exception {
        Department department = new Department(10L, "test1", "test1");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto = departmentConverter.toDto(department);
        when(departmentService.save(departmentDto)).thenReturn(departmentDto);
        mockMvc.perform(post("/department").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(departmentDto))).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(departmentDto.getId().intValue())))
                .andExpect(jsonPath("$.name", equalTo(departmentDto.getName())))
                .andExpect(jsonPath("$.shortName", equalTo(departmentDto.getShortName())));
        verify(departmentService, times(1)).save(departmentDto);
    }

    @Test
    public void saveFailureTest() throws Exception {
        Department department = new Department(11L, "test2", "test2");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto = departmentConverter.toDto(department);
        when(departmentService.save(departmentDto)).thenThrow(Exception.class);
        mockMvc.perform(post("/department").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(departmentDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        Department department = new Department(12L, "test3", "test3");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto = departmentConverter.toDto(department);
        doNothing().when(departmentService).delete(departmentDto.getId());
        mockMvc.perform(delete("/department/{id}", departmentDto.getId())).andExpect(status().isOk());
        verify(departmentService, times(1)).delete(departmentDto.getId());
    }

    @Test
    public void deleteFailureTest() throws Exception {
        Department department = new Department(13L, "test4", "test4");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto = departmentConverter.toDto(department);
        doThrow(Exception.class).when(departmentService).delete(departmentDto.getId());
        mockMvc.perform(delete("/department/{id}", departmentDto.getId())).andExpect(status().isBadRequest());
    }

    @Test
    public void getAllTest() throws Exception {
        Department department1 = new Department(14L, "test5", "test5");
        Department department2 = new Department(15L, "test6", "test6");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto1 = departmentConverter.toDto(department1);
        DepartmentDto departmentDto2 = departmentConverter.toDto(department2);
        List<DepartmentDto> departments = Arrays.asList(departmentDto1, departmentDto2);
        when(departmentService.getAll()).thenReturn(departments);
        MvcResult result = mockMvc.perform(get("/department")).andExpect(status().isOk()).andReturn();
        List<DepartmentDto> returned = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<DepartmentDto>>() {
                });
        assertEquals(departments.size(), returned.size());
        for (int i = 0; i < returned.size(); i++) {
            assertEquals(departments.get(i), returned.get(i));
        }
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        Department department = new Department(16L, "test7", "test7");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto = departmentConverter.toDto(department);
        when(departmentService.findById(departmentDto.getId())).thenReturn(departmentDto);
        mockMvc.perform(get("/department/{id}", departmentDto.getId())).andExpect(status().isOk());
        verify(departmentService, times(1)).findById(departmentDto.getId());
    }

    @Test
    public void findByIdFailureTest() throws Exception {
        Department department = new Department(16L, "test7", "test7");
        DepartmentConverter departmentConverter = new DepartmentConverter();
        DepartmentDto departmentDto = departmentConverter.toDto(department);
        when(departmentService.findById(departmentDto.getId())).thenThrow(Exception.class);
        mockMvc.perform(get("/department/{id}", departmentDto.getId())).andExpect(status().isBadRequest());
    }
}
