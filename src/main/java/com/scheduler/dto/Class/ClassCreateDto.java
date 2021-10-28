package com.scheduler.dto.Class;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ClassCreateDto {

    @NotBlank(message = "Class type cannot be empty")
    @NotEmpty
    private String classType;

    @NotNull(message = "id cannot be null")
    private UUID professorId;

    @NotNull(message = "id cannot be null")
    private UUID courseId;
}
