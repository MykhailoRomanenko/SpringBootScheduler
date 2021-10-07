package com.scheduler.mapper;

import com.scheduler.dto.Course.CourseResponseDto;
import com.scheduler.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "program.id", target = "programId")
    CourseResponseDto mapToResponse(Course course);

    List<CourseResponseDto> mapToResponses(List<Course> courses);
}
