package com.scheduler.dto.Program;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ProgramCreateDto {

    @NotBlank(message = "Program name cannot be empty")
    @NotEmpty
    private String name;

    @NotBlank(message = "Faculty name cannot be empty")
    @NotEmpty
    private String faculty;
}
