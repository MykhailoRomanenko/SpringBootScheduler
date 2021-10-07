package com.scheduler.mapper;

import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.entity.Professor;
import com.scheduler.model.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    ProfessorResponseDto mapToResponse(Professor entity);

    List<ProfessorResponseDto> mapToResponses(List<Professor> entity);

    default String mapPositionToString(Position position) {
        return position.getDescription();
    }
}
