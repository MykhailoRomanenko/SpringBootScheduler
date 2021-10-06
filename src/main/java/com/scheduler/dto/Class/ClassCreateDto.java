package com.scheduler.dto.Class;

import lombok.Data;

import java.util.UUID;

@Data
public class ClassCreateDto {
    private UUID professorId;
    private UUID courseId;
    private String classType;
}
