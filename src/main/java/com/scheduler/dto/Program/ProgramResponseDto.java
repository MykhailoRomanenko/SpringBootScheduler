package com.scheduler.dto.Program;

import lombok.Data;

import java.util.UUID;

@Data
public class ProgramResponseDto {

    private UUID id;
    private String name;
    private String faculty;
}
