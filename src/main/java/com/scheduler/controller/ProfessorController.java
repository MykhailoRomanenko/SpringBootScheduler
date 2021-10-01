package com.scheduler.controller;

import com.scheduler.entity.Professor;
import com.scheduler.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/professors")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public void setProfessorService(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Professor>> findAll() {
        return ok(professorService.findAll());
    }
}
