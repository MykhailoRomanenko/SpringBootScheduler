package com.scheduler.controller;

import com.scheduler.service.ClassService;
import org.springframework.stereotype.Controller;

@Controller
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }
}
