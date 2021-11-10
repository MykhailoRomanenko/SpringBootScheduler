package com.scheduler.controller;

import com.scheduler.dto.Class.ClassCreateDto;
import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.service.ClassService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@ConditionalOnProperty(
        value="controller.class.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/classes")
@Validated
public class ClassController extends BaseController{

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClassResponseDto>> findAll() {
        return ok(classService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDto> findById(@PathVariable UUID id) {
        return ok(classService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ClassResponseDto> save(@Valid @RequestBody ClassCreateDto classCreateDto) {
        return ok(classService.save(classCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponseDto> update(@Valid @RequestBody ClassCreateDto classCreateDto,
                                                   @PathVariable UUID id) {
        return ok(classService.update(id, classCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        classService.deleteById(id);
        return ok().build();
    }
}
