package com.scheduler.controller;

import com.scheduler.dto.Course.CourseCreateDto;
import com.scheduler.dto.Course.CourseResponseDto;
import com.scheduler.dto.Program.ProgramResponseDto;
import com.scheduler.service.CourseService;
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
        value = "controller.course.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/courses")
public class CourseController extends BaseController {

    private final CourseService courseService;
    private final ProgramService programService;

    public CourseController(CourseService courseService,
                            ProgramService programService) {
        this.courseService = courseService;
        this.programService = programService;
    }

    @GetMapping("/all")
    public String findAll(Model model, @AuthenticationPrincipal OidcUser principal) {
        List<CourseResponseDto> courses = courseService.findAll();
        List<ProgramResponseDto> programs = programService.findAll();
        CourseResponseDto course = new CourseResponseDto();
        model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
        model.addAttribute("courses", courses);
        model.addAttribute("programs", programs);
        model.addAttribute("course", course);
        return "course/course";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> findById(@PathVariable UUID id) {
        return ok(courseService.findById(id));
    }

    @PostMapping("")
    public String save(Model model, @Valid @ModelAttribute("course") CourseCreateDto course,
                       BindingResult bindingResult, @AuthenticationPrincipal OidcUser principal) {
        if (bindingResult.hasErrors()) {
            List<CourseResponseDto> courses = courseService.findAll();
            List<ProgramResponseDto> programs = programService.findAll();
            model.addAttribute("courses", courses);
            model.addAttribute("programs", programs);
            model.addAttribute("course", course);
            model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
            return "course/course";
        }
        courseService.save(course);
        return "redirect:courses/all";
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDto> update(@PathVariable UUID id, @Valid @RequestBody CourseCreateDto course) {
        return ok(courseService.update(id, course));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable UUID id) {
        courseService.deleteById(id);
        return "redirect:all";
    }
}
