package com.scheduler.dto.Course;

import lombok.Data;

import java.util.UUID;

@Data
public class CourseCreateDto {

    private String name;
    private Integer year;
    private UUID programId;
}
