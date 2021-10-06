package com.scheduler.mapper;

import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.entity.Class;
import com.scheduler.model.ClassType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(source = "professor.id", target = "professorId")
    @Mapping(source = "course.id", target = "courseId")
    ClassResponseDto mapToResponse(Class entity);

    List<ClassResponseDto> mapToResponses(List<Class> classes);

    default String mapClassTypeToString(ClassType classType) {
        return classType.getDescription();
    }
}
