package com.scheduler.dto.Class;


import com.scheduler.model.ClassType;
import lombok.Data;

import java.util.UUID;

@Data
public class ClassResponseDto {
    private UUID professorId;
    private UUID courseId;
    private String classType;

}
