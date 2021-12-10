package com.scheduler.mapper;

import com.scheduler.dto.Course.CourseResponseDto;
import com.scheduler.entity.Course;
import com.scheduler.model.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "program.id", target = "programId")
    @Mapping(source = "program.name", target = "programName")
    @Mapping(source = "program.faculty", target = "programFaculty")
    CourseResponseDto mapToResponse(Course course);

    List<CourseResponseDto> mapToResponses(List<Course> courses);

    default String mapFacultyToString(Faculty faculty) {
        return faculty.getDescription();
    }
}
