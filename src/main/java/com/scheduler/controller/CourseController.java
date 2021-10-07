package com.scheduler.controller;

import com.scheduler.dto.Course.CourseCreateDto;
import com.scheduler.dto.Course.CourseResponseDto;
import com.scheduler.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@ConditionalOnProperty(
        value="controller.course.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseResponseDto>> findAll() {
        return ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> findById(@PathVariable UUID id) {
        return ok(courseService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<CourseResponseDto> save(@RequestBody CourseCreateDto course) {
        return ok(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> update(@PathVariable UUID id, @RequestBody CourseCreateDto course) {
        return ok(courseService.update(id, course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        courseService.deleteById(id);
        return ok().build();
    }
}
