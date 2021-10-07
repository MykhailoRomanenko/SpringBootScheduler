package com.scheduler.controller;

import com.scheduler.dto.ScheduleRecord.ScheduleRecordCreateDto;
import com.scheduler.dto.ScheduleRecord.ScheduleRecordResponseDto;
import com.scheduler.service.ScheduleRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/scheduleRecords")
public class ScheduleRecordController {

    private final ScheduleRecordService scheduleRecordService;

    public ScheduleRecordController(ScheduleRecordService scheduleRecordService) {
        this.scheduleRecordService = scheduleRecordService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleRecordResponseDto>> findAll() {
        return ok(scheduleRecordService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleRecordResponseDto> findById(@PathVariable UUID id) {
        return ok(scheduleRecordService.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ScheduleRecordResponseDto> save(@RequestBody ScheduleRecordCreateDto scheduleRecordCreateDto) {
        return ok(scheduleRecordService.save(scheduleRecordCreateDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleRecordResponseDto> update(@PathVariable UUID id,
                                                            @RequestBody ScheduleRecordCreateDto scheduleRecordCreateDto) {
        return ok(scheduleRecordService.update(id, scheduleRecordCreateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        scheduleRecordService.deleteById(id);
        return ok().build();
    }
}
