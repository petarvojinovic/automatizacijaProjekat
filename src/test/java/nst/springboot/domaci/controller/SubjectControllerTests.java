package nst.springboot.domaci.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import nst.springboot.domaci.dto.DepartmentDto;
import nst.springboot.domaci.dto.SubjectDto;
import nst.springboot.domaci.service.SubjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
@WebMvcTest(SubjectController.class)
public class SubjectControllerTests {
    @MockBean
    private SubjectService subjectService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveSuccessTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        when(subjectService.save(subjectDto)).thenReturn(subjectDto);
        mockMvc.perform(post("/subject").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(subjectDto))).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.id", equalTo(subjectDto.getId().intValue())))
                .andExpect(jsonPath("$.name", equalTo(subjectDto.getName())))
                .andExpect(jsonPath("$.esbp", equalTo(subjectDto.getEsbp())))
                .andExpect(jsonPath("$.departmentDto.id", equalTo(subjectDto.getDepartmentDto().getId().intValue())))
                .andExpect(jsonPath("$.departmentDto.name", equalTo(subjectDto.getDepartmentDto().getName())))
                .andExpect(jsonPath("$.departmentDto.shortName", equalTo(subjectDto.getDepartmentDto().getShortName())));
        verify(subjectService, times(1)).save(subjectDto);
    }

    @Test
    public void saveFailureTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        when(subjectService.save(subjectDto)).thenThrow(Exception.class);
        mockMvc.perform(post("/subject").contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(subjectDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteSuccessTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        doNothing().when(subjectService).delete(subjectDto.getId());
        mockMvc.perform(delete("/subject/{id}", subjectDto.getId())).andExpect(status().isOk());
        verify(subjectService, times(1)).delete(subjectDto.getId());
    }

    @Test
    public void deleteFailureTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        doThrow(Exception.class).when(subjectService).delete(subjectDto.getId());
        mockMvc.perform(delete("/subject/{id}", subjectDto.getId()));
    }

    @Test
    public void getAllTest() throws Exception {
        SubjectDto subjectDto1 = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        SubjectDto subjectDto2 = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        List<SubjectDto> subjectDtos = Arrays.asList(subjectDto1, subjectDto2);
        when(subjectService.getAll()).thenReturn(subjectDtos);
        MvcResult result = mockMvc.perform(get("/subject")).andExpect(status().isOk()).andReturn();
        List<SubjectDto> returned = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<SubjectDto>>() {
                });
        assertEquals(subjectDtos.size(), returned.size());
        for (int i = 0; i < returned.size(); i++) {
            assertEquals(subjectDtos.get(i), returned.get(i));
        }
    }

    @Test
    public void findByIdSuccessTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        when(subjectService.findById(subjectDto.getId())).thenReturn(subjectDto);
        mockMvc.perform(get("/subject/{id}", subjectDto.getId())).andExpect(status().isOk());
        verify(subjectService, times(1)).findById(subjectDto.getId());
    }

    @Test
    public void findByIdFailureTest() throws Exception {
        SubjectDto subjectDto = new SubjectDto(10L, "test1", 4, new DepartmentDto(3L, "test1", "test1"));
        when(subjectService.findById(subjectDto.getId())).thenThrow(Exception.class);
        mockMvc.perform(get("/subject/{id}", subjectDto.getId())).andExpect(status().isBadRequest());
    }
}
