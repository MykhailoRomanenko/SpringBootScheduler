package com.scheduler;

import com.scheduler.controller.ProfessorController;
import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.entity.Professor;
import com.scheduler.mapper.ProfessorMapper;
import com.scheduler.model.Position;
import com.scheduler.service.ProfessorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = ProfessorController.class)
public class ProfessorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @MockBean
    private ProfessorMapper professorMapper;

    @Test
    public void whenGetById_thenStatusOk() throws Exception {
        Professor professor = new Professor();
        professor.setName("Vasya");
        professor.setPosition(Position.ASSISTANT);
        ProfessorResponseDto responseDto = professorMapper.mapToResponse(professor);
        when(professorService.findById(any(UUID.class))).thenReturn(responseDto);
        this.mockMvc.perform(get("/api/v1/professors/12"))
                .andDo(print()).andExpect(status().isUnauthorized())
                .andReturn();
    }

}
