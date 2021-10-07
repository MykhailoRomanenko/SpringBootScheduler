package com.scheduler.dto.Professor;

import lombok.Data;

import java.util.UUID;

@Data
public class ProfessorResponseDto {

    private UUID id;
    private String name;
    private String position;
}
