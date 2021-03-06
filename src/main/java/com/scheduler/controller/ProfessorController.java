package com.scheduler.controller;

import com.scheduler.dto.Professor.ProfessorCreateDto;
import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@ConditionalOnProperty(
        value = "controller.professor.active",
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
    public String findAll(Model model, @AuthenticationPrincipal OidcUser principal) {
        model.addAttribute("professors", professorService.findAll());
        model.addAttribute("professor", new ProfessorResponseDto());
        model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
        return "professors/professors";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable UUID id, Model model) {
        model.addAttribute("professor", professorService.findById(id));
        return "professors/findProfessorById";
    }

    @PostMapping
    public String save(Model model, @Valid @ModelAttribute("professor") ProfessorCreateDto professorCreateDto, BindingResult bindingResult, @AuthenticationPrincipal OidcUser principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
            model.addAttribute("professors", professorService.findAll());
            return "professors/professors";
        }
        professorService.save(professorCreateDto);
        return "redirect:professors/all";
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDto> update(@PathVariable UUID id,
                                                       @Valid @RequestBody ProfessorCreateDto professorCreateDto) {
        return ok(professorService.update(id, professorCreateDto));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable UUID id) {
        professorService.deleteById(id);
        return "redirect:all";
    }
}
