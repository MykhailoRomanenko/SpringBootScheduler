package com.scheduler.controller;

import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.dto.ScheduleRecord.ScheduleRecordCreateDto;
import com.scheduler.dto.ScheduleRecord.ScheduleRecordResponseDto;
import com.scheduler.service.ClassService;
import com.scheduler.service.ScheduleRecordService;
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
        value = "controller.schedulerecord.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/scheduleRecords")
public class ScheduleRecordController extends BaseController {

    private final ScheduleRecordService scheduleRecordService;
    private final ClassService classService;

    public ScheduleRecordController(ScheduleRecordService scheduleRecordService,
                                    ClassService classService) {
        this.scheduleRecordService = scheduleRecordService;
        this.classService = classService;
    }

    @GetMapping("/all")
    public String findAll(Model model, @AuthenticationPrincipal OidcUser principal) {
        List<ScheduleRecordResponseDto> schedules = scheduleRecordService.findAll();
        List<ClassResponseDto> classes = classService.findAll();
        ScheduleRecordCreateDto schedule = new ScheduleRecordCreateDto();
        model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
        model.addAttribute("schedules", schedules);
        model.addAttribute("schedule", schedule);
        model.addAttribute("classes", classes);
        return "schedule/schedule";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleRecordResponseDto> findById(@PathVariable UUID id) {
        return ok(scheduleRecordService.findById(id));
    }

    @PostMapping("")
    public String save(Model model, @Valid @ModelAttribute("schedule") ScheduleRecordCreateDto scheduleRecordCreateDto,
                       BindingResult bindingResult, @AuthenticationPrincipal OidcUser principal) {
        if (bindingResult.hasErrors()) {
            List<ScheduleRecordResponseDto> schedules = scheduleRecordService.findAll();
            List<ClassResponseDto> classes = classService.findAll();
            model.addAttribute("schedules", schedules);
            model.addAttribute("classes", classes);
            model.addAttribute("isAdmin", principal != null && principal.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equalsIgnoreCase("SCOPE_admin")));
            return "schedule/schedule";
        }
        scheduleRecordService.save(scheduleRecordCreateDto);
        return "redirect:scheduleRecords/all";
    }

    @ResponseBody
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleRecordResponseDto> update(@PathVariable UUID id,
                                                            @Valid @RequestBody ScheduleRecordCreateDto scheduleRecordCreateDto) {
        return ok(scheduleRecordService.update(id, scheduleRecordCreateDto));
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable UUID id) {
        scheduleRecordService.deleteById(id);
        return "redirect:all";
    }
}
