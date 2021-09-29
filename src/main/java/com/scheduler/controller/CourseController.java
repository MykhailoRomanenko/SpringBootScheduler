package com.scheduler.controller;

import com.scheduler.service.CourseService;
import org.springframework.stereotype.Controller;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
}
