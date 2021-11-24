package com.scheduler.controller;

import com.scheduler.dto.Professor.ProfessorCreateDto;
import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@ConditionalOnProperty(
        value="controller.professor.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/professors")
@Validated
public class ProfessorController extends BaseController {

    private ProfessorService professorService;

    @Autowired
    public void setProfessorService(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professors";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable UUID id, Model model) {
        model.addAttribute("professor", professorService.findById(id));
        return "professor";
    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<ProfessorResponseDto> save(@Valid @RequestBody ProfessorCreateDto professorCreateDto) {
        return ok(professorService.save(professorCreateDto));
    }
    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> update(@PathVariable UUID id,
                                                       @Valid @RequestBody ProfessorCreateDto professorCreateDto) {
        return ok(professorService.update(id, professorCreateDto));
    }
    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        professorService.deleteById(id);
        return ok().build();
    }
}
