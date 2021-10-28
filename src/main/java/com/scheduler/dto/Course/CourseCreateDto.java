package com.scheduler.dto.Course;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
public class CourseCreateDto {

    @NotBlank(message = "Course name cannot be empty")
    @NotEmpty
    private String name;

    @Min(1)
    @Max(6)
    private Integer year;

    @NotNull(message = "id cannot be null")
    private UUID programId;
}
