package com.scheduler.controller;

import com.scheduler.service.ProfessorService;
import org.springframework.stereotype.Controller;

@Controller
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
}
