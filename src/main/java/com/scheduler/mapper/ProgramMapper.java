package com.scheduler.mapper;

import com.scheduler.dto.Program.ProgramResponseDto;
import com.scheduler.entity.Program;
import com.scheduler.model.Faculty;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramMapper {

    ProgramResponseDto mapToResponse(Program program);

    List<ProgramResponseDto> mapToResponses(List<Program> program);

    default String mapFacultyToString(Faculty faculty) {
        return faculty.getDescription();
    }
}
