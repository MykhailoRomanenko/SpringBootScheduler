package com.scheduler.dto.Professor;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ProfessorCreateDto {

    @NotBlank(message = "Professor name cannot be empty")
    @NotEmpty
    private String name;

    @NotBlank(message = "Position name cannot be empty")
    @NotEmpty
    private String position;

    public ProfessorCreateDto() {
    }
}
