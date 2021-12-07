package com.scheduler.dto.Professor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class ProfessorCreateDto {

    @NotBlank(message = "Professor name cannot be blank")
    private String name;

    @NotBlank(message = "Position name cannot be blank")
    private String position;
}
