package com.scheduler.dto.Class;

import lombok.Data;

import java.util.UUID;

@Data
public class ClassResponseDto {

    private UUID id;
    private UUID professorId;
    private String professorName;
    private UUID courseId;
    private String courseName;
    private String classType;
}
