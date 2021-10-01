package com.scheduler.controller;

import com.scheduler.entity.Course;
import com.scheduler.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<Course>> findAll() {
        return ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable UUID id) {
        return ok(courseService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return ok(courseService.save(course));
    }

    @PutMapping("")
    public ResponseEntity<Course> update(@RequestBody Course course) {
        return ok(courseService.update(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        courseService.deleteById(id);
        return ok().build();
    }
}
