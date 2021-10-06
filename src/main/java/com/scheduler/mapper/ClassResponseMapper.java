package com.scheduler.mapper;


import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.model.ClassType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.scheduler.entity.Class;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClassResponseMapper {

    @Mapping(target = "professorId", source = "entity.professor.id")
    @Mapping(target = "courseId", source = "entity.course.id")
    ClassResponseDto classEntityToDto(Class entity);

    List<ClassResponseDto> classEntityListToDto(List<Class> classes);

    default String getClassTypeString(ClassType classType) {
        return classType.getDescription();
    }
}
