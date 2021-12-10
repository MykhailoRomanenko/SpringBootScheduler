package com.scheduler.controller;

import com.scheduler.dto.Program.ProgramCreateDto;
import com.scheduler.dto.Program.ProgramResponseDto;
import com.scheduler.service.ProgramService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@ConditionalOnProperty(
        value = "controller.class.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/programs")
public class ProgramController extends BaseController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/all")
    public String findAll(Model model, @AuthenticationPrincipal OidcUser principal) {
        List<ProgramResponseDto> programs = programService.findAll();
        ProgramResponseDto program = new ProgramResponseDto();
        model.addAttribute("programs", programs);
        model.addAttribute("program", program);
        model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
        return "program/program";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> findById(@PathVariable UUID id) {
        return ok(programService.findById(id));
    }

    @PostMapping("")
    public String save(Model model, @Valid @ModelAttribute("program") ProgramCreateDto program,
                       BindingResult bindingResult, @AuthenticationPrincipal OidcUser principal) {
        if (bindingResult.hasErrors()) {
            List<ProgramResponseDto> programs = programService.findAll();
            model.addAttribute("programs", programs);
            model.addAttribute("program", program);
            model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
            return "program/program";
        }
        programService.save(program);
        return "redirect:programs/all";
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> update(@PathVariable UUID id,
                                                     @Valid @RequestBody ProgramCreateDto programCreateDto) {
        return ok(programService.update(id, programCreateDto));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable UUID id) {
        programService.deleteById(id);
        return "redirect:all";
    }
}
