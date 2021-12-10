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
    @Mapping(source = "professor.name", target = "professorName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.name", target = "courseName")
    ClassResponseDto mapToResponse(Class entity);

    List<ClassResponseDto> mapToResponses(List<Class> classes);

    default String mapClassTypeToString(ClassType classType) {
        return classType.getDescription();
    }
}
