package com.scheduler.controller;

import com.scheduler.dto.Class.ClassCreateDto;
import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.dto.Course.CourseResponseDto;
import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.dto.ScheduleRecord.ScheduleRecordCreateDto;
import com.scheduler.dto.ScheduleRecord.ScheduleRecordResponseDto;
import com.scheduler.service.ClassService;
import com.scheduler.service.CourseService;
import com.scheduler.service.ProfessorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@ConditionalOnProperty(
        value="controller.class.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/classes")
public class ClassController extends BaseController{

    private final ClassService classService;
    private final ProfessorService professorService;
    private final CourseService courseService;

    public ClassController(ClassService classService,
                           ProfessorService professorService,
                           CourseService courseService) {
        this.classService = classService;
        this.professorService = professorService;
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public String findAll(Model model, @AuthenticationPrincipal OidcUser principal) {
        List<ClassResponseDto> classes = classService.findAll();
        List<ProfessorResponseDto> professors = professorService.findAll();
        List<CourseResponseDto> courses = courseService.findAll();
        ClassResponseDto clazz = new ClassResponseDto();
        model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
        model.addAttribute("courses", courses);
        model.addAttribute("professors", professors);
        model.addAttribute("clazz", clazz);
        model.addAttribute("classes", classes);
        return "class/class";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDto> findById(@PathVariable UUID id) {
        return ok(classService.findById(id));
    }

    @PostMapping("")
    public String save(Model model, @Valid @ModelAttribute("clazz") ClassCreateDto clazz,
                       BindingResult bindingResult, @AuthenticationPrincipal OidcUser principal) {
        if (bindingResult.hasErrors()) {
            List<ClassResponseDto> classes = classService.findAll();
            List<ProfessorResponseDto> professors = professorService.findAll();
            List<CourseResponseDto> courses = courseService.findAll();
            model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
            model.addAttribute("courses", courses);
            model.addAttribute("professors", professors);
            model.addAttribute("clazz", clazz);
            model.addAttribute("classes", classes);
            return "class/class";
        }
        classService.save(clazz);
        return "redirect:classes/all";
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<ClassResponseDto> update(@Valid @RequestBody ClassCreateDto classCreateDto,
                                                   @PathVariable UUID id) {
        return ok(classService.update(id, classCreateDto));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable UUID id) {
        classService.deleteById(id);
        return "redirect:all";
    }
}
