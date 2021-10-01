package com.scheduler.service;

import com.scheduler.entity.Course;
import com.scheduler.exception.NotFoundException;
import com.scheduler.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course findById(UUID id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Course with id=%s not found", id)));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(UUID id) {
        courseRepository.deleteById(id);
    }
}
