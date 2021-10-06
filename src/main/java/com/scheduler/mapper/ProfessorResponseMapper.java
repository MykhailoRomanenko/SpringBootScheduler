package com.scheduler.mapper;

import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.entity.Professor;
import com.scheduler.model.ClassType;
import com.scheduler.model.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfessorResponseMapper {

    ProfessorResponseDto entityToResponseDto(Professor entity);

    default String getClassTypeString(Position position) {
        return position.getDescription();
    }
}
