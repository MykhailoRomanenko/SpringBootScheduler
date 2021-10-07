package com.scheduler.service;

import com.scheduler.dto.Course.CourseCreateDto;
import com.scheduler.dto.Course.CourseResponseDto;
import com.scheduler.entity.Course;
import com.scheduler.entity.Program;
import com.scheduler.exception.NotFoundException;
import com.scheduler.mapper.CourseMapper;
import com.scheduler.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ProgramService programService;

    public CourseService(CourseRepository courseRepository,
                         CourseMapper courseMapper,
                         ProgramService programService) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.programService = programService;
    }

    public CourseResponseDto findById(UUID id) {
        return courseMapper.mapToResponse(findEntityById(id));
    }

    public Course findEntityById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Course with id=%s not found", id)));
    }

    public List<CourseResponseDto> findAll() {
        return courseMapper.mapToResponses(courseRepository.findAll());
    }

    public CourseResponseDto save(CourseCreateDto courseCreateDto) {
        Course course = new Course();
        return updateCourse(courseCreateDto, course);
    }

    public CourseResponseDto update(UUID id, CourseCreateDto courseCreateDto) {
        Course course = findEntityById(id);
        return updateCourse(courseCreateDto, course);
    }

    private CourseResponseDto updateCourse(CourseCreateDto courseCreateDto, Course course) {
        course.setName(courseCreateDto.getName());
        course.setYear(courseCreateDto.getYear());
        if (courseCreateDto.getProgramId() != null) {
            Program program = programService.findEntityById(courseCreateDto.getProgramId());
            course.setProgram(program);
        }
        return courseMapper.mapToResponse(courseRepository.save(course));
    }

    public void deleteById(UUID id) {
        courseRepository.deleteById(id);
    }
}
