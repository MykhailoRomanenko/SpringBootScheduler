package com.scheduler.controller;

import com.scheduler.dto.Professor.ProfessorCreateDto;
import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@ConditionalOnProperty(
        value="controller.professor.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/professors")
public class ProfessorController {

    private ProfessorService professorService;

    @Autowired
    public void setProfessorService(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfessorResponseDto>> findAll() {
        return ok(professorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> findById(@PathVariable UUID id) {
        return ok(professorService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ProfessorResponseDto> save(@RequestBody ProfessorCreateDto professorCreateDto) {
        return ok(professorService.save(professorCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> update(@PathVariable UUID id,
                                                       @RequestBody ProfessorCreateDto professorCreateDto) {
        return ok(professorService.update(id, professorCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        professorService.deleteById(id);
        return ok().build();
    }
}
