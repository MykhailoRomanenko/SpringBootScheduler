package com.scheduler.controller;

import com.scheduler.service.ProgramService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@ConditionalOnProperty(
        value="controller.class.active",
        havingValue = "true",
        matchIfMissing = true)
@RequestMapping("/api/v1/programs")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProgramResponseDto>> findAll() {
        return ok(programService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> findById(@PathVariable UUID id) {
        return ok(programService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ProgramResponseDto> save(@RequestBody ProgramCreateDto programCreateDto) {
        return ok(programService.save(programCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramResponseDto> update(@PathVariable UUID id,
                                                     @RequestBody ProgramCreateDto programCreateDto) {
        return ok(programService.update(id, programCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        programService.deleteById(id);
        return ok().build();
    }
}
